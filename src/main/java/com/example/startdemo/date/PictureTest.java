package com.example.startdemo.date;

import com.btime.util.GsonUtil;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sun.image.codec.jpeg.JPEGCodec;
import net.coobird.thumbnailator.Thumbnails;
import sun.awt.image.JPEGImageDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author xuweihang@qbb.com
 * @date 2019-12-25 19:07
 */
public class PictureTest {
    private static Gson gson = GsonUtil.createGson();
    static String desktop = "/Users/xuweihang/Desktop/pic/";
    static String desktop2 = "/Users/xuweihang/Desktop/pic2/";

    public static void main(String[] args) throws Exception {
        dealPic();
    }

    private static void dealPic() throws Exception {
//        String data = "[[\"35180\",\"张老师讲故事\",\"10\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"35188.0\",\"聪明宝宝听音乐\",\"22\",\"0.0\",\"41.0\",\"12.0\",\"36.0\",\"胎教\",\"益智\"],[\"46756\",\"中国经典童话故事\",\"47\",\"\",\"\",\"24.0\",\"60.0\",\"故事\",\"\"],[\"53476\",\"字母歌曲\",\"27\",\"\",\"\",\"24.0\",\"60.0\",\"儿歌\",\"\"],[\"53956\",\"三只小猪\",\"15\",\"\",\"\",\"12.0\",\"36.0\",\"儿歌\",\"故事\"],[\"54116\",\"信谊世界精选图画书\",\"11\",\"\",\"\",\"24.0\",\"60.0\",\"故事\",\"\"],[\"54144.0\",\"儿童智慧故事\",\"31\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"54208\",\"千字文\",\"47\",\"\",\"\",\"24.0\",\"72.0\",\"益智\",\"\"],[\"54478\",\"培养完美人格故事书\",\"21\",\"\",\"\",\"24.0\",\"48.0\",\"故事\",\"\"],[\"54494\",\"小乌龟富兰克林\",\"11\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"54562\",\"小熊宝宝\",\"10\",\"\",\"\",\"12.0\",\"36.0\",\"故事\",\"\"],[\"54636\",\"小鲤鱼历险记\",\"8\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"54678\",\"幸福的种子\",\"30\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"54748\",\"快乐学成语\",\"33\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"55022\",\"海豚绘本系列\",\"39\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"55114\",\"猫猫一家亲\",\"22\",\"\",\"\",\"24.0\",\"60.0\",\"故事\",\"\"],[\"55268\",\"经典童话名著\",\"51\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"55290\",\"聪明豆\",\"30\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"55360\",\"蒲公英图书馆\",\"10\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"55366\",\"蓝皮鼠大脸猫\",\"18\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"55490\",\"儿童国学《道德经》\",\"31\",\"\",\"\",\"48.0\",\"84.0\",\"益智\",\"\"],[\"55510\",\"金龟子讲故事\",\"25\",\"\",\"\",\"24.0\",\"48.0\",\"故事\",\"\"],[\"55752\",\"鼹鼠的故事\",\"27\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"60872\",\"小兔汤姆\",\"16\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"61174\",\"彼得兔的故事\",\"43\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"63568\",\"中国传统故事\",\"22\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"63598\",\"宝贝益智音乐\",\"20\",\"0.0\",\"41.0\",\"0.0\",\"24.0\",\"胎教\",\"益智\"],[\"77854\",\"上学歌\",\"28\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"84494\",\"磨出我的英文耳朵\",\"21\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"87286\",\"小飞侠彼得潘\",\"21\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"91120\",\"圣诞儿歌\",\"20\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"94162\",\"3岁宝宝睡前故事\",\"16\",\"\",\"\",\"36.0\",\"60.0\",\"故事\",\"哄睡\"],[\"94534\",\"半小时爸爸\",\"47\",\"\",\"\",\"24.0\",\"60.0\",\"故事\",\"\"],[\"94648\",\"三分钟儿童故事\",\"17\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"94662\",\"儿童故事全记录\",\"48\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"94682\",\"宝贝这样做真棒\",\"30\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"94720\",\"聪明宝宝\",\"32\",\"\",\"\",\"24.0\",\"48.0\",\"故事\",\"\"],[\"116740\",\"《冰清姐姐讲故事》\",\"12\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"哄睡\"],[\"122334\",\"小蜻蜓故事会\",\"40\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"130044\",\"甜甜妈妈讲故事\",\"22\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"142566\",\"国学经典\",\"48\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"150364\",\"【甜枣阿姨】贝贝熊系列丛书\",\"33\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"152268\",\"茜茜公主讲故事\",\"11\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"154426\",\"婴儿安睡音乐\",\"25\",\"\",\"\",\"0.0\",\"24.0\",\"哄睡\",\"\"],[\"155262\",\"星星姐姐讲故事之晚安宝贝\",\"28\",\"\",\"\",\"12.0\",\"36.0\",\"故事\",\"哄睡\"],[\"158964\",\"【原创】10后最喜爱的儿歌（1）\",\"19\",\"\",\"\",\"12.0\",\"36.0\",\"儿歌\",\"\"],[\"164820\",\"经典认知类英文儿歌——动物篇\",\"12\",\"\",\"\",\"24.0\",\"48.0\",\"儿歌\",\"\"],[\"165672\",\"经典认知类英文儿歌——字母数字篇\",\"21\",\"\",\"\",\"24.0\",\"48.0\",\"儿歌\",\"\"],[\"166638\",\"值得珍藏的世界童谣之法国童谣\",\"14\",\"\",\"\",\"12.0\",\"60.0\",\"儿歌\",\"\"],[\"166668\",\"值得珍藏的世界童谣之英美童谣\",\"13\",\"\",\"\",\"12.0\",\"60.0\",\"儿歌\",\"\"],[\"168184\",\"喵喵小故事\",\"17\",\"\",\"\",\"24.0\",\"48.0\",\"故事\",\"\"],[\"168920\",\"儿童文学名家名著\",\"38\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"169620\",\"献给母亲的花环\",\"29\",\"\",\"\",\"60.0\",\"84.0\",\"故事\",\"\"],[\"176154\",\"猪迪克睡前音乐\",\"9\",\"\",\"\",\"0.0\",\"24.0\",\"哄睡\",\"\"],[\"176356\",\"Sandy老师讲故事\",\"16\",\"\",\"\",\"24.0\",\"48.0\",\"故事\",\"\"],[\"176972\",\"猪迪克儿歌\",\"37\",\"\",\"\",\"12.0\",\"36.0\",\"儿歌\",\"\"],[\"177316\",\"儿歌卡通金曲\",\"21\",\"\",\"\",\"36.0\",\"72.0\",\"儿歌\",\"\"],[\"178340\",\"芭比之梦想豪宅\",\"40\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"182018\",\"宝宝们喜欢听的儿歌\",\"33\",\"\",\"\",\"24.0\",\"48.0\",\"儿歌\",\"\"],[\"183050\",\"星星字母歌\",\"32\",\"\",\"\",\"24.0\",\"48.0\",\"儿歌\",\"\"],[\"184118\",\"贝贝熊系列故事\",\"23\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"186130\",\"卤蛋阿姨讲故事\",\"50\",\"\",\"\",\"24.0\",\"60.0\",\"故事\",\"\"],[\"186296\",\"贝乐虎故事\",\"28\",\"\",\"\",\"24.0\",\"48.0\",\"故事\",\"\"],[\"186974\",\"幼儿礼仪 常识篇\",\"21\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"188276\",\"儿童绘本故事\",\"28\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"189120\",\"贝乐虎古诗\",\"20\",\"\",\"\",\"24.0\",\"72.0\",\"益智\",\"\"],[\"198540\",\"开思英语儿歌专辑《快乐一天》\",\"20\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"199714\",\"开思儿歌专辑《经典儿歌》\",\"12\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"200374\",\"开思儿歌 《经典英语儿歌1》\",\"17\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"201086\",\"宝贝的绘本时间（亲子共读）\",\"25\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"201864\",\"开思儿歌 《经典英语儿歌2》\",\"10\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"204240\",\"兔乖乖说晚安·睡前故事\",\"12\",\"\",\"\",\"12.0\",\"72.0\",\"哄睡\",\"\"],[\"205954\",\"杨子阿姨讲故事\",\"15\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"205988\",\"摇篮演奏曲第一辑\",\"10\",\"0.0\",\"42.0\",\"0.0\",\"24.0\",\"哄睡\",\"胎教\"],[\"206283\",\"摇篮演奏曲第二辑\",\"9\",\"0.0\",\"42.0\",\"0.0\",\"24.0\",\"哄睡\",\"胎教\"],[\"207155\",\"让故事陪宝贝儿去梦乡\",\"14\",\"\",\"\",\"12.0\",\"72.0\",\"哄睡\",\"\"],[\"208470\",\"儿童故事《伊索寓言》\",\"18\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"益智\"],[\"209493\",\"二十四节气\",\"23\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"210931\",\"小眼睛看世界\",\"51\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"211217\",\"格林童话小故事\",\"24\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"213249\",\"贝贝熊双语系列\",\"12\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"213637\",\"贝贝熊的故事\",\"30\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"216886\",\"麻糖中文故事-安徒生童话\",\"10\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"218220\",\"宝贝学钢琴：晨听古典音乐磨耳朵\",\"42\",\"0.0\",\"42.0\",\"\",\"\",\"胎教\",\"\"],[\"221400\",\"宝宝听儿歌\",\"9\",\"\",\"\",\"5.0\",\"24.0\",\"儿歌\",\"\"],[\"221598\",\"2-6岁儿童读物\",\"51\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"222697\",\"白妈咪讲《成语故事》\",\"26\",\"\",\"\",\"60.0\",\"72.0\",\"哄睡\",\"\"],[\"223081\",\"《小鸡彩虹》动画原声\",\"26\",\"\",\"\",\"6.0\",\"36.0\",\"故事\",\"\"],[\"223342\",\"【听宝爸讲故事】\",\"33\",\"0.0\",\"42.0\",\"\",\"\",\"胎教\",\"\"],[\"224621\",\"【原创】10后最喜爱的儿歌（二）\",\"20\",\"\",\"\",\"5.0\",\"72.0\",\"儿歌\",\"\"],[\"227341\",\"《伊索寓言》\",\"44\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"228003\",\"经典神话故事——爱心共读\",\"13\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"益智\"],[\"228565\",\"胎教小故事\",\"42\",\"0.0\",\"42.0\",\"0.0\",\"12.0\",\"故事\",\"胎教\"],[\"229001\",\"必听100个小科普\",\"50\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"229389\",\"天意妈妈讲《弟子规》\",\"42\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"229823\",\"宝宝巴士启蒙音乐剧\",\"31\",\"\",\"\",\"6.0\",\"36.0\",\"故事\",\"益智\"],[\"230032\",\"音乐磨耳朵|给孩子的古典音乐启蒙课|童年情景\",\"13\",\"0.0\",\"42.0\",\"\",\"\",\"胎教\",\"益智\"],[\"230316\",\"关于家人的绘本故事\",\"21\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"哄睡\"],[\"230766\",\"二十四节气歌-盛龙国学儿歌\",\"24\",\"\",\"\",\"12.0\",\"72.0\",\"儿歌\",\"益智\"],[\"231392\",\"《声律启蒙》节奏诵读（上）·孩子最爱\",\"15\",\"\",\"\",\"3.0\",\"24.0\",\"益智\",\"\"],[\"232045\",\"睡前故事【精选】\",\"23\",\"\",\"\",\"12.0\",\"72.0\",\"哄睡\",\"故事\"],[\"232221\",\"绘本情商故事\",\"35\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"232443\",\"《魔法书》\",\"36\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"233276\",\"三字经\",\"14\",\"\",\"\",\"12.0\",\"72.0\",\"益智\",\"故事\"],[\"233367\",\"一起读英语绘本1\",\"29\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"益智\"],[\"233619\",\"中华传统文化新儿歌之新古诗歌\",\"12\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"益智\"],[\"233641\",\"一起读英语绘本2\",\"25\",\"\",\"\",\"12.0\",\"72.0\",\"儿歌\",\"益智\"],[\"233758\",\"弟子规\",\"11\",\"\",\"\",\"12.0\",\"72.0\",\"益智\",\"故事\"],[\"234366\",\"朗朗妈妈晚安故事【绘本中华故事-十二生肖】\",\"12\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"益智\"],[\"234862\",\"宝妈树洞\",\"32\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"\"],[\"236248\",\"育灵童国学《左传》儿童经典诵读文明朗读\",\"18\",\"\",\"\",\"12.0\",\"72.0\",\"益智\",\"故事\"],[\"236794\",\"朗朗妈妈晚安绘本故事【聪明豆系列】\",\"26\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"236814\",\"晓鹿老师-数学儿歌\",\"33\",\"\",\"\",\"12.0\",\"36.0\",\"益智\",\"\"],[\"237246\",\"300好习惯养成\",\"30\",\"\",\"\",\"24.0\",\"48.0\",\"故事\",\"益智\"],[\"237492\",\"小学语文二年级下册\",\"38\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"237494\",\"小学语文三年级上册\",\"40\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"237636\",\"朱朱姐姐讲故事\",\"47\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"哄睡\"],[\"237771\",\"晚安爸爸讲故事\",\"28\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"哄睡\"],[\"238384\",\"棉花糖妈妈讲故事\",\"43\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"\"],[\"238715\",\"小兔汤姆系列故事（成长的烦恼）\",\"25\",\"\",\"\",\"12.0\",\"48.0\",\"故事\",\"\"],[\"239105\",\"实录大自然的声音\",\"14\",\"0.0\",\"42.0\",\"0.0\",\"6.0\",\"哄睡\",\"胎教\"],[\"239929\",\"儿童版 八十天环游地球  凡尔纳世界经典名著\",\"16\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"240294\",\"宝妈弟子规\",\"8\",\"\",\"\",\"12.0\",\"72.0\",\"益智\",\"\"],[\"240942\",\"苏苏宝宝 国学胎教启蒙故事\",\"8\",\"0.0\",\"42.0\",\"3.0\",\"24.0\",\"益智\",\"胎教\"],[\"241293\",\"【岩子趣说西游】\",\"48\",\"\",\"\",\"48.0\",\"72.0\",\"故事\",\"\"],[\"241851\",\"鱼丸睡前故事\",\"14\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"哄睡\"],[\"242456\",\"睡前国学馆\",\"18\",\"\",\"\",\"12.0\",\"72.0\",\"哄睡\",\"益智\"],[\"242541\",\"爱多多阿姨讲睡前童话故事-思维情商性格培养\",\"24\",\"\",\"\",\"12.0\",\"72.0\",\"哄睡\",\"益智\"],[\"242997\",\"睡前故事—儿童财商教育绘本丛书\",\"48\",\"\",\"\",\"60.0\",\"72.0\",\"哄睡\",\"益智\"],[\"243398\",\"爸爸讲睡前故事\",\"18\",\"0.0\",\"42.0\",\"\",\"\",\"胎教\",\"\"],[\"244199\",\"中华成语故事\",\"30\",\"\",\"\",\"60.0\",\"72.0\",\"故事\",\"益智\"],[\"244889\",\"飞米粒家庭教育早教听听成语故事\",\"10\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"245221\",\"我爱学百科 植物板块 第一季\",\"33\",\"\",\"\",\"50.0\",\"72.0\",\"益智\",\"\"],[\"253723\",\"小朋友最爱听的中国神话故事\",\"23\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"254223\",\"趣味动物十万个为什么（少儿儿童版）\",\"28\",\"\",\"\",\"50.0\",\"72.0\",\"益智\",\"\"],[\"254261\",\"巧虎三字经\",\"15\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"254459\",\"布鲁童音—中国古代神话故事\",\"22\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"254584\",\"安徒生童话\",\"24\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"254802\",\"布鲁童音—幼儿好习惯\",\"40\",\"\",\"\",\"18.0\",\"48.0\",\"故事\",\"\"],[\"255266\",\"朗朗妈妈晚安故事-贝贝熊系列丛书\",\"33\",\"\",\"\",\"24.0\",\"72.0\",\"哄睡\",\"故事\"],[\"255659\",\"小故事大道理\",\"24\",\"\",\"\",\"0.0\",\"60.0\",\"故事\",\"益智\"],[\"255807\",\"猴小姐的布老虎——哇哦世界\",\"10\",\"\",\"\",\"18.0\",\"72.0\",\"哄睡\",\"故事\"],[\"256269\",\"超级飞侠 第一季\",\"26\",\"\",\"\",\"30.0\",\"72.0\",\"故事\",\"\"],[\"256439\",\"小马嘚嘚讲神话故事\",\"31\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"257450\",\"千寻妈咪·每晚一个绘本故事\",\"19\",\"\",\"\",\"12.0\",\"72.0\",\"哄睡\",\"故事\"],[\"257482\",\"动物故事\",\"31\",\"\",\"\",\"0.0\",\"60.0\",\"故事\",\"\"],[\"258898\",\"阿里巴巴和四十大盗—老墨家族\",\"13\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"258998\",\"经典英文儿歌集\",\"22\",\"\",\"\",\"0.0\",\"72.0\",\"儿歌\",\"\"],[\"259003\",\"晚安大自然第二季\",\"37\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"259063\",\"儿戏讲故事之《欧洲精灵故事》\",\"12\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"259222\",\"自然拼读儿歌\",\"20\",\"\",\"\",\"18.0\",\"72.0\",\"益智\",\"\"],[\"259548\",\"小飞侠彼得潘（完本）\",\"27\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"260906\",\"兔小贝国学系列之《千字文》\",\"31\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"260924\",\"蔷薇别墅的老鼠\",\"38\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"261751\",\"布鲁童音—英语经典儿歌\",\"41\",\"\",\"\",\"0.0\",\"72.0\",\"儿歌\",\"\"],[\"262800\",\"卷毛妈妈讲故事\",\"17\",\"\",\"\",\"0.0\",\"60.0\",\"故事\",\"\"],[\"262852\",\"小鼠波波 幼儿英语启蒙绘本 英语早教\",\"20\",\"\",\"\",\"48.0\",\"84.0\",\"故事\",\"\"],[\"263734\",\"巴巴爸爸全集\",\"22\",\"\",\"\",\"12.0\",\"72.0\",\"故事\",\"\"],[\"263934\",\"沐云少儿学堂—声律启蒙\",\"30\",\"\",\"\",\"24.0\",\"72.0\",\"益智\",\"\"],[\"264940\",\"贝乐虎儿歌\",\"30\",\"\",\"\",\"0.0\",\"48.0\",\"儿歌\",\"\"],[\"267510\",\"浩然爸爸讲民间故事\",\"40\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"267545\",\"国际大奖 | 高质量儿童睡前故事\",\"14\",\"\",\"\",\"0.0\",\"48.0\",\"哄睡\",\"故事\"],[\"268771\",\"0-3岁的宝宝音乐感知唤醒\",\"8\",\"\",\"\",\"0.0\",\"36.0\",\"益智\",\"\"],[\"268772\",\"0-3岁宝宝的音乐认知提升\",\"9\",\"\",\"\",\"0.0\",\"36.0\",\"益智\",\"\"],[\"268774\",\"专属宝宝的原创安睡音乐\",\"10\",\"\",\"\",\"0.0\",\"36.0\",\"哄睡\",\"\"],[\"268880\",\"智慧故事\",\"41\",\"\",\"\",\"0.0\",\"48.0\",\"故事\",\"益智\"],[\"268892\",\"成语故事\",\"46\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"益智\"],[\"271745\",\"露露姐姐故事汇\",\"20\",\"\",\"\",\"12.0\",\"60.0\",\"故事\",\"\"],[\"274443\",\"【摇儿篮篮】国学经典-弟子规\",\"16\",\"\",\"\",\"36.0\",\"84.0\",\"益智\",\"\"],[\"275297\",\"西游记-幼儿白话版【已完结】\",\"23\",\"\",\"\",\"36.0\",\"84.0\",\"益智\",\"\"],[\"276127\",\"熊猫天天－安全教育\",\"41\",\"\",\"\",\"24.0\",\"72.0\",\"哄睡\",\"\"],[\"276131\",\"熊猫天天－安徒生童话系列\",\"44\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"276418\",\"熊猫天天 - 乐于助人故事\",\"11\",\"\",\"\",\"24.0\",\"72.0\",\"哄睡\",\"故事\"],[\"276422\",\"熊猫天天 - 谦虚故事\",\"36\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"276539\",\"熊猫天天 - 日本民间童话故事\",\"42\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"益智\"],[\"276665\",\"心小蕾故事会\",\"37\",\"\",\"\",\"12.0\",\"48.0\",\"故事\",\"\"],[\"277878\",\"我的安全书 | 3-6岁儿童安全教育必备\",\"10\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"278927\",\"论语诵读篇\",\"20\",\"\",\"\",\"48.0\",\"84.0\",\"益智\",\"\"],[\"280232\",\"声律启蒙\",\"16\",\"\",\"\",\"24.0\",\"72.0\",\"益智\",\"\"],[\"282222\",\"幼儿阅读：听出宝宝智慧\",\"40\",\"\",\"\",\"0.0\",\"48.0\",\"故事\",\"益智\"],[\"282367\",\"萤火虫：双语童话故事\",\"50\",\"\",\"\",\"60.0\",\"84.0\",\"故事\",\"益智\"],[\"282369\",\"儿童英语歌谣（起步）\",\"24\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"282375\",\"儿童英语歌谣（进阶）\",\"20\",\"\",\"\",\"18.0\",\"54.0\",\"儿歌\",\"\"],[\"282376\",\"儿童英语歌谣（超越）\",\"40\",\"\",\"\",\"24.0\",\"60.0\",\"儿歌\",\"\"],[\"283003\",\"小树林系列绘本\",\"8\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"283006\",\"阿凡提的故事：智慧篇\",\"29\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"益智\"],[\"283214\",\"最美图画书：狮子拔牙\",\"20\",\"\",\"\",\"12.0\",\"48.0\",\"故事\",\"\"],[\"284493\",\"思然姐姐快乐读童谣\",\"50\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"284865\",\"弟子规\",\"50\",\"\",\"\",\"0.0\",\"72.0\",\"益智\",\"\"],[\"284934\",\"快乐宝贝睡前故事·温馨卷\",\"13\",\"\",\"\",\"12.0\",\"48.0\",\"故事\",\"\"],[\"284936\",\"快乐宝贝睡前故事·知识卷\",\"20\",\"\",\"\",\"0.0\",\"36.0\",\"故事\",\"\"],[\"284940\",\"阳光宝贝启蒙365夜故事·珍藏版\",\"16\",\"\",\"\",\"12.0\",\"48.0\",\"故事\",\"\"],[\"284941\",\"阳光宝贝启蒙365夜故事·分享版\",\"21\",\"\",\"\",\"12.0\",\"48.0\",\"故事\",\"\"],[\"284955\",\"大图童话3\",\"11\",\"\",\"\",\"0.0\",\"36.0\",\"故事\",\"\"],[\"285233\",\"漫画上下五千年系列：春秋争霸\",\"15\",\"\",\"\",\"48.0\",\"84.0\",\"益智\",\"\"],[\"285250\",\"漫画上下五千年系列：战国七雄\",\"16\",\"\",\"\",\"48.0\",\"84.0\",\"益智\",\"\"],[\"285839\",\"科学密码之我们的身体\",\"9\",\"\",\"\",\"36.0\",\"84.0\",\"益智\",\"\"],[\"294102\",\"阿狸智慧晚安故事\",\"40\",\"\",\"\",\"12.0\",\"60.0\",\"益智\",\"\"],[\"306117\",\"彩虹365夜故事\",\"50\",\"\",\"\",\"24.0\",\"60.0\",\"故事\",\"\"],[\"319074\",\"拼音王国|幼小衔接·宝宝巴士教辅\",\"34\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"319453\",\"熊熊欢乐颂\",\"13\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"256310\",\"熊熊乐园\",\"52\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"241835\",\"廖彩杏吴敏兰共荐\",\"11\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"35212\",\"西游记\",\"16\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"54116\",\"信谊世界精选图画书\",\"11\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"54252\",\"可爱的鼠小弟\",\"12\",\"\",\"\",\"0.0\",\"48.0\",\"故事\",\"\"],[\"54328\",\"嘟嘟和巴豆\",\"10\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"54336\",\"国家地理\",\"9\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"54584\",\"小猫当当\",\"10\",\"\",\"\",\"0.0\",\"36.0\",\"故事\",\"\"],[\"60442\",\"动物百科\",\"44\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"61864\",\"神奇校车\",\"11\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"78344\",\"阿波林的小世界\",\"18\",\"\",\"\",\"0.0\",\"36.0\",\"故事\",\"\"],[\"94656\",\"儿童听世界\",\"31\",\"\",\"\",\"0.0\",\"36.0\",\"故事\",\"\"],[\"96512\",\"女巫温妮\",\"7\",\"\",\"\",\"24.0\",\"72.0\",\"故事\",\"\"],[\"111676\",\"《小王子》宝宝故事会\",\"27\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"114358\",\"《神奇校车》图画版\",\"20\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"118072\",\"西顿动物记：小狼威尼\",\"8\",\"\",\"\",\"36.0\",\"72.0\",\"益智\",\"\"],[\"325312\",\"机灵宠物车\",\"26\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"315466\",\"超级飞侠第三季\",\"22\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"315546\",\"超级飞侠第四季\",\"26\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"304848\",\"猪猪侠之竞球小英雄\",\"50\",\"\",\"\",\"36.0\",\"72.0\",\"故事\",\"\"],[\"34978.0\",\"轻松英文音韵童谣\",\"18\",\"\",\"\",\"6.0\",\"36.0\",\"儿歌\",\"\"],[\"37854\",\"Phonics Kids 学会英语发音\",\"26\",\"\",\"\",\"12.0\",\"48.0\",\"儿歌\",\"\"],[\"53418\",\"粤语卡通儿歌\",\"35\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"53476\",\"字母歌曲\",\"27\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"53956\",\"三只小猪\",\"15\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"77632\",\"Schnuffel童声专辑\",\"30\",\"\",\"\",\"3.0\",\"24.0\",\"儿歌\",\"\"],[\"83054\",\"童一首歌\",\"7\",\"\",\"\",\"3.0\",\"24.0\",\"儿歌\",\"\"],[\"84258\",\"经典磨耳朵英语童谣\",\"24\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"84494\",\"磨出我的英文耳朵\",\"21\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"91120.0\",\"圣诞儿歌\",\"20\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"124376\",\"全国少儿歌手推荐歌曲\",\"31\",\"\",\"\",\"12.0\",\"72.0\",\"儿歌\",\"\"],[\"164820\",\"经典认知类英文儿歌——动物篇\",\"12\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"165672\",\"经典认知类英文儿歌——字母数字篇\",\"21\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"166638\",\"值得珍藏的世界童谣之法国童谣\",\"14\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"166668\",\"值得珍藏的世界童谣之英美童谣\",\"13\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"177316\",\"儿歌卡通金曲\",\"21\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"182018\",\"宝宝们喜欢听的儿歌\",\"33\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"183050\",\"星星字母歌\",\"32\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"189778\",\"《快乐可可狮身体律动歌》-分月龄家庭早教\",\"55\",\"\",\"\",\"12.0\",\"36.0\",\"儿歌\",\"\"],[\"198540\",\"开思英语儿歌专辑《快乐一天》\",\"20\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"199714.0\",\"开思儿歌专辑《经典儿歌》\",\"12\",\"\",\"\",\"3.0\",\"48.0\",\"儿歌\",\"\"],[\"200374\",\"开思儿歌 《经典英语儿歌1》\",\"17\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"201864\",\"开思儿歌 《经典英语儿歌2》\",\"10\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"203064\",\"开思儿歌 《经典英语儿歌3》\",\"9\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"203744\",\"开思儿歌 《经典英语儿歌4》\",\"9\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"209925\",\"宝狄亲子儿歌\",\"11\",\"\",\"\",\"12.0\",\"72.0\",\"儿歌\",\"\"],[\"221400\",\"宝宝听儿歌\",\"9\",\"\",\"\",\"3.0\",\"36.0\",\"儿歌\",\"\"],[\"223081\",\"《小鸡彩虹》动画原声\",\"26\",\"\",\"\",\"3.0\",\"36.0\",\"儿歌\",\"\"],[\"224621\",\"【原创】10后最喜爱的儿歌（二）\",\"20\",\"\",\"\",\"3.0\",\"36.0\",\"儿歌\",\"\"],[\"236201\",\"常见英语单词儿歌\",\"49\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"243829\",\"三三儿歌\",\"58\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"254978\",\"天空树\",\"12\",\"\",\"\",\"12.0\",\"72.0\",\"儿歌\",\"\"],[\"259222\",\"自然拼读儿歌\",\"20\",\"\",\"\",\"12.0\",\"72.0\",\"儿歌\",\"\"],[\"261751\",\"布鲁童音—英语经典儿歌\",\"41\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"261771\",\"每天一首英语儿歌\",\"52\",\"\",\"\",\"6.0\",\"72.0\",\"儿歌\",\"\"],[\"264940\",\"贝乐虎儿歌\",\"30\",\"\",\"\",\"3.0\",\"72.0\",\"儿歌\",\"\"],[\"54480\",\"宝宝必背唐诗\",\"20\",\"\",\"\",\"24.0\",\"72.0\",\"益智\",\"\"],[\"229993\",\"玖声诗词儿歌\",\"10\",\"\",\"\",\"24.0\",\"72.0\",\"益智\",\"\"],[\"254261\",\"巧虎三字经\",\"15\",\"\",\"\",\"18.0\",\"36.0\",\"益智\",\"\"],[\"283889\",\"三字经（童声跟读）\",\"4\",\"\",\"\",\"18.0\",\"36.0\",\"益智\",\"\"]]";
//        List<List<String>> qingtingAlbum = gson.fromJson(data, new TypeToken<List<List>>() {
//        }.getType());
        List<Long> channelIds = Arrays.asList(218220L, 255807L);
//        for (List<String> list : qingtingAlbum) {
//            Long channelId = Float.valueOf(list.get(0)).longValue();
//            channelIds.add(channelId);
//        }
        HashSet<Long> set = new HashSet<>(channelIds);

        for (Long channelId : set) {
            String path = desktop + channelId + ".jpg";
            String path2 = desktop2 + channelId + ".jpg";
            try {
                Thumbnails.of(path).size(340, 260).toFile(path2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error:" + channelId);
            }

        }
    }

}
