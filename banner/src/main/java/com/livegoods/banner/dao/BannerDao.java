package com.livegoods.banner.dao;

import com.livegoods.pojo.Banner;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 17/9/20
 * @Description: com.livegoods.banner.dao
 * @version: 1.0
 */
public interface BannerDao {

    List<Banner> selectBanners(Query query);

}
