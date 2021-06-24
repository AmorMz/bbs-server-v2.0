package pers.muzi.bbs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.muzi.bbs.entity.Tag;

import java.util.Date;

/**
 * @author AmorMz
 */
@SpringBootTest
public class Test {

    @Autowired
    private PostDAO postDAO;

    @org.junit.jupiter.api.Test
    public void tagTest() {
        Tag tag = new Tag();
        tag
                .setName("amormz")
                .setPostCount(0)
                .setCreateTime(new Date());
        postDAO.insertTag(tag);

        System.out.println(tag.getId());

    }
}
