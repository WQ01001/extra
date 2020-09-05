import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.crm.active.app.ActiveApplication;
import com.crm.active.entity.Information;
import com.crm.active.entity.Resource;
import com.crm.active.entity.RosterMain;
import com.crm.active.mapper.InformationMapper;
import com.crm.active.mapper.ResourceMapper;
import com.crm.active.mapper.RosterMainMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/**
 * @program: extra
 * @description:
 * @author: wq
 * @create: 2020-09-01 16:52
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
// 启动Spring
@SpringBootTest(classes = ActiveApplication.class)
public class JunitTest {

  public static final Logger log = LoggerFactory.getLogger(JunitTest.class);
  @Autowired InformationMapper informationMapper;
  @Autowired ResourceMapper resourceMapper;
  @Autowired RosterMainMapper rosterMainMapper;
  private final int threadSize = 100;
  private final Queue<Integer> queue = new LinkedBlockingDeque<>();
  private final Queue<Information> rqueue = new LinkedBlockingDeque<>();
  private final CountDownLatch countDownLatch = new CountDownLatch(threadSize);

  @Test
  public void test() {
    //    先处理曾被回收资源
    QueryWrapper<Information> wrapper = new QueryWrapper<>();
    wrapper.eq("type", "unconfirmeddesk");
    List<Information> informationList = informationMapper.selectList(wrapper);
    queue.addAll(
        informationList.stream().map(Information::getResourceid).collect(Collectors.toSet()));
    ExecutorService executorService = Executors.newFixedThreadPool(200);

    for (int i = 0; i < threadSize; i++) {
      executorService.submit(
          () -> {
            while (true) {
              if (queue.size() == 0) {
                countDownLatch.countDown();
                break;
              }
              Integer resourceid = queue.poll();
              Optional.ofNullable(resourceid)
                  .ifPresent(
                      in -> {
                        // 获取最大时间的那个消息就是
                        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
                        QueryWrapper<Information> resourceId =
                            queryWrapper
                                .eq("type", "dispatchresource")
                                .eq("resourceId", resourceid)
                                .orderByDesc("date");
                        List<Information> list = informationMapper.selectList(resourceId);
                        Information information1 = list.get(0);
                        QueryWrapper<RosterMain> rosterMainQueryWrapper = new QueryWrapper<>();
                        rosterMainQueryWrapper
                            .select("roleId")
                            .eq("roster_id", information1.getUserid());
                        RosterMain rosterMain = rosterMainMapper.selectOne(rosterMainQueryWrapper);
                        Integer roleid = rosterMain.getRoleid();
                        if (roleid == 13 || roleid == 14) {
                          Resource resource = new Resource();
                          resource.setRecipient(information1.getUserid());
                          UpdateWrapper<Resource> updateWrapper = new UpdateWrapper<>();
                          updateWrapper.eq("resourceId", information1.getResourceid());
                          resourceMapper.update(resource, updateWrapper);
                        } else {
                          log.info("角色:" + roleid);
                        }
                      });
            }
          });
    }
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /** 处理被回收了但是没有分配记录的资源 */
  @Test
  public void test2() {
    List<Information> informationList = informationMapper.check();
    informationList.forEach(
        i -> {
          QueryWrapper<RosterMain> wrapper = new QueryWrapper<>();
          wrapper.select("roleId").eq("roster_id", i.getUserid());
          RosterMain rosterMain = rosterMainMapper.selectOne(wrapper);

          Optional.ofNullable(rosterMain)
              .ifPresent(
                  role -> {
                    Integer roleid = rosterMain.getRoleid();
                    if (roleid == 13 || roleid == 14) {
                      Resource resource = new Resource();
                      resource.setRecipient(i.getUserid());
                      UpdateWrapper<Resource> updateWrapper = new UpdateWrapper<>();
                      updateWrapper.eq("resourceId", i.getResourceid());
                      resourceMapper.update(resource, updateWrapper);
                    } else {
                      log.info("角色:" + roleid);
                    }
                  });
        });
  }

  /**
   * 处理没有进行回收过的资源 一次分配就点的
   *
   * @throws InterruptedException
   */
  @Test
  public void test3() throws InterruptedException {
    List<Information> others = informationMapper.others();
    rqueue.addAll(others);
    ExecutorService executorService = Executors.newFixedThreadPool(800);
    for (int i = 0; i < threadSize; i++) {
      executorService.submit(
          () -> {
            while (true) {
              if (rqueue.size() == 0) {
                countDownLatch.countDown();
                log.info("队列空了！");
                break;
              }
              Information o = rqueue.poll();
              Integer resourceid = o.getResourceid();
              Resource resource = new Resource();
              String userid = o.getUserid();
              //                查询角色
              QueryWrapper<RosterMain> wrapper = new QueryWrapper<>();
              wrapper.select("roleId").eq("roster_id", o.getUserid());
              RosterMain rosterMain = rosterMainMapper.selectOne(wrapper);
              Optional.ofNullable(rosterMain)
                  .ifPresent(
                      r -> {
                        Integer roleid = rosterMain.getRoleid();
                        if (roleid == 13 || roleid == 14) {
                          resource.setRecipient(userid);
                          resourceMapper.update(
                              resource, new UpdateWrapper<Resource>().eq("resourceId", resourceid));
                        }
                      });
            }
          });
    }
    countDownLatch.await();
  }

  @Test
  public void test4() {
    List<Resource> resources = resourceMapper.select();
    resources.forEach(
        s -> {
          Resource resource = new Resource();
          resource.setRecipient(s.getUserid());
          UpdateWrapper<Resource> updateWrapper = new UpdateWrapper<>();
          resourceMapper.update(resource, updateWrapper.eq("resourceId", s.getResourceid()));
        });
  }
}
