package com.example.startdemo.date.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @description: Instagram Photo配置
 * @author: david.jiang
 * @date: 2021/12/22 4:42 下午
 **/
@Data
public class InstagramPhotoCfg {

    /**
     * 照片墙配置
     */
    private PhotoConfig config = new PhotoConfig();

    /**
     * 长效accessToken
     */
    private String longLivedAccessToken ="";

    /**
     * 长效accessToken是否有效，0:无效 1:有效，
     * 当用户修改密码或者取消授权等情况，会造成token无效
     */
    private Integer isAccessTokenValid = 1;

    /**
     * 图片打点信息 key : 媒体ID
     */
    private Map<String, List<PhotoMark>> marks = Maps.newHashMap();

    /**
     * ins账号名称
     */
    private String username;

    @Data
    public static class PhotoMark {
        /**
         * 打点的x坐标
         */
        private String x;
        /**
         * 打点的y坐标
         */
        private String y;
        /**
         * 商品id
         */
        private String productId;
        /**
         * 商品标题
         */
        private String title;
        /**
         * 商品图片
         */
        private String imageUrl;
        /**
         * 商品售卖价
         */
        private String salesPrice;
        /**
         * 商品货币符号
         */
        private String currency;
    }

    @Data
    public static class PhotoConfig {
        /**
         * 标题
         * 必填：否 字符上限：80个字符
         */
        private String title = "Follow us on Instagram";
        /**
         * 图片间隔
         * 可选项：0%、1%、2%、5%、10%
         */
        private String interval = "0";
        /**
         * 图片点击效果
         * 可选项：1）展示帖子弹窗 2）跳转至对应Post 3）无点击效果
         */
        private String effect = "1";
        /**
         * 图片布局
         * 1）网格展示布局  2）滑动展示布局
         */
        private String layout = "1";
        /**
         * 行数设置
         * 最小值：1行1列
         * 最大值：4行6列
         */
        private String rows = "2";
        /**
         * 列数设置
         * 最小值：1行1列
         * 最大值：4行6列
         */
        private String columns = "4";
        /**
         * 手机端行数设置
         * 最小值：1行1列
         * 最大值：4行6列
         */
        private String phoneRows = "4";
        /**
         * 手机端列数设置
         * 最小值：1行1列
         * 最大值：4行6列
         */
        private String phoneColumns = "2";
        /**
         * 置顶图片媒体ID
         */
        private List<String> topMediaIds = Lists.newArrayList();

        /**
         * hashtag开关
         * 是否开启 0 否  1 是
         */
        private Integer hashTagSwitch = 1;

        /**
         * 展示选项
         * 1：只展示 2：不展示
         */
        private Integer showHashTagOption = 1;
        /**
         * 只展示的hashtag
         */
        private String showHashTags = "";
        /**
         * 不展示的hashtag
         */
        private String nonShowHashTags = "";
    }
}
