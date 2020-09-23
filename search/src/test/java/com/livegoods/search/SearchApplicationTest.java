package com.livegoods.search;

import com.livegoods.pojo.Item;
import com.livegoods.search.dao.ItemDao4ES;
import com.livegoods.search.pojo.Item4ES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 18/9/20
 * @Description: com.livegoods.search
 * @version: 1.0
 */
@SpringBootTest(classes = {SearchApplication.class})
@RunWith(SpringRunner.class)
public class SearchApplicationTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ItemDao4ES itemDao4ES;
    @Value("${fdfs.nginx.prefix}")
    private String nginxPrefix;

    @Test
    public  void  testInitES(){
        //从mongodb查询所有item数据
        List<Item> itemList = mongoTemplate.findAll(Item.class);
        //将item数据转换为item4es
        List<Item4ES> item4ESList=new ArrayList<>();
        for (Item item : itemList) {
            Item4ES i = new Item4ES();
            i.setId(item.getId());
            i.setTitle(item.getTitle());
            i.setCity(item.getCity());
            i.setHouseType(item.getHouseType4Search());
            i.setImg( nginxPrefix+item.getImg());
            i.setPrice("<h3>" + item.getPrice() + "</h3>");
            i.setRentType(item.getRentType());
            item4ESList.add(i);
        }
        itemDao4ES.batchInsert(item4ESList);

    }
}
