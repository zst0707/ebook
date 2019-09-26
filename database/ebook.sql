/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : ebook

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-09-26 10:13:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` char(32) NOT NULL,
  `adminname` varchar(50) DEFAULT NULL,
  `adminpwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('a2', 'Loco', '123');

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `bid` char(32) NOT NULL,
  `bname` varchar(200) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `currPrice` decimal(8,2) DEFAULT NULL,
  `discount` decimal(3,1) DEFAULT NULL,
  `press` varchar(100) DEFAULT NULL,
  `publishtime` char(10) DEFAULT NULL,
  `edition` int(11) DEFAULT NULL,
  `pageNum` int(11) DEFAULT NULL,
  `wordNum` int(11) DEFAULT NULL,
  `printtime` char(10) DEFAULT NULL,
  `booksize` int(11) DEFAULT NULL,
  `paper` varchar(50) DEFAULT NULL,
  `cid` char(32) DEFAULT NULL,
  `image_w` varchar(100) DEFAULT NULL,
  `image_b` varchar(100) DEFAULT NULL,
  `orderBy` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bid`),
  KEY `orderBy` (`orderBy`),
  KEY `FK_t_book_t_category` (`cid`),
  CONSTRAINT `FK_t_book_t_category` FOREIGN KEY (`cid`) REFERENCES `t_category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('0EE8A0AE69154287A378FB110FF2C780', 'Java核心技术：卷Ⅰ基础知识（原书第8版）', '昊斯特曼', '98.00', '67.60', '6.9', '机械工业出版社', '2008-6-1', '1', '694', '0', '2008-6-1', '16', '胶版纸', 'C4DD8CA232864B31A367EE135D86382C', 'book_img/20285763-1_w.jpg', 'book_img/20285763-1_b.jpg', '12');
INSERT INTO `t_book` VALUES ('1286B13F0EA54E4CB75434762121486A', 'Java核心技术 卷I：基础知识(第9版·英文版)(上、下册)', '霍斯特曼', '109.00', '75.20', '6.9', '人民邮电出版社', '2013-7-1', '1', '2000', '1197000', '2013-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23280479-1_w.jpg', 'book_img/23280479-1_b.jpg', '65');
INSERT INTO `t_book` VALUES ('13EBF9B1FDE346A683A1C45BD6773ECB', 'Java开发实战1200例（第Ⅱ卷）（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '无', '99.00', '68.30', '6.9', '清华大学出版社', '2015-6-19', '1', '1000', '1754000', '2011-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21110930-1_w_1.jpg', 'book_img/21110930-1_b.jpg', '83');
INSERT INTO `t_book` VALUES ('1A15DC5E8A014A58862ED741D579B530', 'Java深入解析——透析Java本质的36个话题', '梁勇', '49.00', '33.80', '6.9', '电子工业出版社', '2013-11-1', '1', '298', '424000', '2013-11-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23363997-1_w_1.jpg', 'book_img/23363997-1_b.jpg', '86');
INSERT INTO `t_book` VALUES ('1A40F7EEDF7249B5873819494BE85F11', '西游记', '吴承恩', '59.00', '40.70', '6.9', '人民教育出版社', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', '113946AA10EF4DD29E46F6B0B85B1285', 'book_img/1D01053A0F07424DBEFCD3F4FA16CA4A_xyj1.JPG', 'book_img/F8E73E41E4764B15BEFB048A46641A8F_xyj.JPG', '118');
INSERT INTO `t_book` VALUES ('2287E1677186458D941E597697F805BF', '外婆的道歉信', '弗雷德里克·巴克曼', '59.00', '40.70', '6.9', '天津人民出版社', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', 'A9CFBED0F77746C5BD751F2502FAB2CD', 'book_img/64C4A1AAF55649FE88DFD19A3F6BF335_wp1.jpg', 'book_img/44987A0E0524417A8473FA916D6BFA6E_wp.jpg', '125');
INSERT INTO `t_book` VALUES ('37F75BEAE1FE46F2B14674923F1E7987', '数据结构与算法分析Java语言描述 第2版', '韦斯', '55.00', '38.00', '6.9', '机械工业出版社', '2009-1-1', '2', '440', '0', '2009-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20417467-1_w.jpg', 'book_img/20417467-1_b.jpg', '32');
INSERT INTO `t_book` VALUES ('3DD187217BF44A99B86DD18A4DC628BA', 'Java核心技术 卷1 基础知识（原书第9版）', '霍斯特曼，科内尔', '119.00', '82.10', '6.9', '机械工业出版社', '2014-1-1', '1', '704', '0', '2014-1-1', '16', '胶版纸', 'F5C091B3967442A2B35EFEFC4EF8746F', 'book_img/23362142-1_w_1.jpg', 'book_img/23362142-1_b.jpg', '9');
INSERT INTO `t_book` VALUES ('400D94DE5A0742B3A618FC76DF107183', 'JavaScript宝典（第7版）（配光盘）', '古德曼', '128.00', '88.30', '6.9', '清华大学出版社', '2013-1-1', '1', '1012', '1657000', '2013-1-1', '32', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23169892-1_w.jpg', 'book_img/23169892-1_b.jpg', '88');
INSERT INTO `t_book` VALUES ('4491EA4832E04B8B94F334B71E871983', 'Java语言程序设计：进阶篇（原书第8版）', '梁勇', '79.00', '54.50', '6.9', '机械工业出版社', '2011-6-1', '1', '507', '0', '2011-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21117631-1_w_1.jpg', 'book_img/21117631-1_b.jpg', '48');
INSERT INTO `t_book` VALUES ('4BF6D97DD18A4B77B8DED9B057577F8F', 'Java Web从入门到精通（附光盘1张）（连续8月Java类全国零售排行前2名，27小时视频，951个经典实例、369项面试真题、596项测试史上最全资源库）', '明日科技', '69.80', '48.20', '6.9', '清华大学出版社', '2012-9-1', '1', '547', '979000', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22862056-1_w.jpg', 'book_img/22862056-1_b.jpg', '23');
INSERT INTO `t_book` VALUES ('4E44405DAFB7413E8A13BBFFBEE73AC7', 'JavaScript经典实例', '鲍尔斯', '78.00', '53.80', '6.9', '中国电力出版社', '2012-3-1', '1', '512', '625000', '2012-3-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22692811-1_w.jpg', 'book_img/22692811-1_b.jpg', '68');
INSERT INTO `t_book` VALUES ('52B0EDFF966E4A058BDA5B18EEC698C4', '亮剑Java Web项目开发案例导航(含DVD光盘1张)', '朱雪琴', '69.00', '41.40', '6.0', '电子工业出版社', '2012-3-1', '1', '526', '875000', '2012-3-1', '16', '胶版纸', '922E6E2DB04143D39C9DDB26365B3EE8', 'book_img/22623766-1_w.jpg', 'book_img/22623766-1_b.jpg', '81');
INSERT INTO `t_book` VALUES ('568A94A03ABE4F2885EDD9B8C852189E', '恶意', '东野圭吾', '59.00', '40.70', '6.9', '南海出版公司', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', 'A9CFBED0F77746C5BD751F2502FAB2CD', 'book_img/E43FB95B643C4BA4B435C8584EE5236A_ey.jpg', 'book_img/741FD51D55F049AE8170E9962236C936_ey1.jpg', '113');
INSERT INTO `t_book` VALUES ('5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '明日科技', '59.80', '41.30', '6.9', '清华大学出版社', '2012-9-1', '3', '564', '1013000', '2012-9-1', '16', '胶版纸', 'C4DD8CA232864B31A367EE135D86382C', 'book_img/22862060-1_w.jpg', 'book_img/22862060-1_b.jpg', '1');
INSERT INTO `t_book` VALUES ('749CD9537C5B45FDA30DDAA680F5DA50', '三国演义', '罗贯中', '60.00', '48.00', '8.0', '黑龙江美术出版社', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', '113946AA10EF4DD29E46F6B0B85B1285', 'book_img/C783B57BA04B4C7696C8943D555A95FA_sgyy1.JPG', 'book_img/BE563BDB19FB46F5A7D8DC237D640E30_sgyy.JPG', '116');
INSERT INTO `t_book` VALUES ('7D7FE81293124793BDB2C6FF1F1C943D', '21天学通Java(第6版)（中文版累计销量超30000册）', 'Rogers Cadenhead', '55.00', '38.00', '6.9', '人民邮电出版社', '2013-4-1', '1', '410', '781000', '2013-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23219731-1_w.jpg', 'book_img/23219731-1_b.jpg', '46');
INSERT INTO `t_book` VALUES ('7FD7F50B15F74248AA769798909F8653', 'Java网络编程（第3版）——O’Reilly Java系列', '哈诺德', '85.00', '51.00', '6.0', '中国电力出版社', '2005-11-1', '1', '718', '668000', '2005-11-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9062293-1_w.jpg', 'book_img/9062293-1_b.jpg', '35');
INSERT INTO `t_book` VALUES ('819FF56E4423462394E6F83882F78975', '学通Java Web的24堂课（配光盘）（软件开发羊皮卷）', '陈丹丹', '79.80', '55.10', '6.9', '清华大学出版社', '2011-6-1', '1', '718', '1488000', '2011-6-1', '16', '胶版纸', 'D45D96DA359A4FEAB3AB4DCF2157FC06', 'book_img/21118835-1_w_1.jpg', 'book_img/21118835-1_b.jpg', '91');
INSERT INTO `t_book` VALUES ('89A57D099EA14026A5C3D10CFC10C22C', 'Java 2实用教程（第4版）（21世纪高等学校计算机基础实用规划教材）', '耿祥义', '39.50', '31.60', '8.0', '清华大学出版社', '2012-8-1', '4', '479', '782000', '2012-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22844118-1_w.jpg', 'book_img/22844118-1_b.jpg', '73');
INSERT INTO `t_book` VALUES ('8DD0ADF2665B40899E09ED2983DC3F7B', 'jQuery权威指南（被公认的权威的、易学的jQuery实战教程，多次重印，热销中）', '陶国荣', '59.00', '40.70', '6.9', '机械工业出版社', '2011-1-1', '1', '385', '0', '2011-1-1', '16', '胶版纸', 'C4DD8CA232864B31A367EE135D86382C', 'book_img/21006995-1_w_1.jpg', 'book_img/21006995-1_b.jpg', '75');
INSERT INTO `t_book` VALUES ('8E16D59BA4C34374A68029AE877613C4', '轻量级Java EE企业应用实战（第3版）：Struts 2＋Spring 3＋Hibernate整合开发(含CD光盘1张)', '李刚', '99.00', '68.30', '6.9', '电子工业出版社', '2012-4-1', '1', '816', '1440000', '2012-4-1', '16', '胶版纸', 'D45D96DA359A4FEAB3AB4DCF2157FC06', 'book_img/22685703-1_w.jpg', 'book_img/22685703-1_b.jpg', '6');
INSERT INTO `t_book` VALUES ('9201DECE2DD44CD8B1B9531CA43BA732', '失乐园', '渡边淳一', '59.00', '40.70', '6.9', '珠海出版社', '2007-10-01', '1', '374', '48700', '2007-10-10', '16', '胶版纸', 'A9CFBED0F77746C5BD751F2502FAB2CD', 'book_img/B57F70F99A3A4AFB9C2D99520E2765CD_0001-w.jpg', 'book_img/5F4F0D458E3E461B8FA9918628F1695C_0001-b.jpg', '103');
INSERT INTO `t_book` VALUES ('926B8F31C5D04F61A72F66679A0CCFFD', 'JavaScript编程精解（华章程序员书库）（JavaScript之父高度评价并强力推荐，系统学习JS首选！）', '哈弗贝克', '49.00', '33.80', '6.9', '械工业出版社', '2012-9-1', '1', '162', '0', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22873894-1_w.jpg', 'book_img/22873894-1_b.jpg', '70');
INSERT INTO `t_book` VALUES ('97437DAD03FA456AA7D6154614A43B55', 'HTML、CSS、JavaScript网页制作从入门到精通（两万读者的选择，经久不衰的超级畅销书最新升级版！网页制作学习者入门必读经典！）', '刘西杰', '49.00', '32.90', '6.7', '人民邮电出版社', '2012-12-24', '1', '450', '705000', '2012-12-24', '16', '胶版纸', '84ECE401C2904DBEA560D04A581A66D9', 'book_img/22928649-1_w.jpg', 'book_img/22928649-1_b.jpg', '3');
INSERT INTO `t_book` VALUES ('9FBD51A7C02D4F5B9862CD2EBBF5CA04', 'Flash ActionScript 3.0全站互动设计', '刘欢 ', '69.80', '48.20', '6.9', '人民邮电出版社', '2012-10-1', '1', '488', '760000', '2012-10-1', '16', '胶版纸', 'D45D96DA359A4FEAB3AB4DCF2157FC06', 'book_img/22886581-1_w.jpg', 'book_img/22886581-1_b.jpg', '96');
INSERT INTO `t_book` VALUES ('A46A0F48A4F649AE9008B38EA48FAEBA', 'Java编程全能词典(含DVD光盘2张)', '明日科技', '98.00', '65.70', '6.7', '电子工业出版社', '2010-3-1', '1', '486', '496000', '2010-3-1', '32', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20813806-1_w_1.jpg', 'book_img/20813806-1_b.jpg', '90');
INSERT INTO `t_book` VALUES ('A5A6F27DCD174614850B26633A0B4605', 'JavaScript模式', '斯特凡洛夫', '38.00', '22.80', '6.0', '中国电力出版社', '2012-7-1', '1', '208', '253000', '2012-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22819430-1_w.jpg', 'book_img/22819430-1_b.jpg', '61');
INSERT INTO `t_book` VALUES ('A7220EF174704012830E066FDFAAD4AD', 'Spring 3.0就这么简单（国内原创的Java敏捷开发图书，展现作者Spring原创开源项目ROP开发的全过程，所有项目工程均以Maven组织）', '陈雄华', '59.00', '40.70', '6.9', '人民邮电出版社', '2013-1-1', '1', '380', '530000', '2013-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22938474-1_w.jpg', 'book_img/22938474-1_b.jpg', '77');
INSERT INTO `t_book` VALUES ('A73523E8651B4DEC80B1FC64A670A391', '小王子', '安东尼·德·圣-埃克苏佩里', '59.00', '40.70', '6.9', '江苏凤凰文艺出版社', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', '47ED6D6F601747AB8AFD6E14E6231DC5', 'book_img/B8B40145E76D467997EBA7BACCB40296_xwz1.jpg', 'book_img/38757EDCB21645D6883DA586B0C35E19_xwz.JPG', '123');
INSERT INTO `t_book` VALUES ('A7EFD99367C9434682A790635D3C5FDF', 'Java Web技术整合应用与项目实战（JSP+Servlet+Struts2+Hibernate+Spring3）', '张志锋', '98.00', '67.60', '6.9', '清华大学出版社', '2013-6-1', '1', '878', '0', '2013-6-1', '16', '胶版纸', '922E6E2DB04143D39C9DDB26365B3EE8', 'book_img/23266270-1_w.jpg', 'book_img/23266270-1_b.jpg', '92');
INSERT INTO `t_book` VALUES ('AE0935F13A214436B8599DE285A86220', 'JavaScript基础教程(第8版)(经典JavaScript入门书 涵盖Ajax和jQuery)', 'Tom Negrino　Dori Smith', '69.00', '47.60', '6.9', '人民邮电出版社', '2012-4-1', '1', '392', '694000', '2012-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22717349-1_w.jpg', 'book_img/22717349-1_b.jpg', '37');
INSERT INTO `t_book` VALUES ('B7A7DA7A94E54054841EED1F70C3027C', '锋利的jQuery(第2版)(畅销书升级版，增加jQuery Mobile和性能优化)', '单东林', '49.00', '33.80', '6.9', '人民邮电出版社', '2012-7-1', '2', '380', '598000', '2012-7-1', '16', '胶版纸', 'C3F9FAAF4EA64857ACFAB0D9C8D0E446', 'book_img/22786088-1_w.jpg', 'book_img/22786088-1_b.jpg', '10');
INSERT INTO `t_book` VALUES ('BD1CB005E4A04DCA881DA8689E21D4D0', 'jQuery UI开发指南', 'Eric Sarrion', '39.00', '26.90', '6.9', '人民邮电出版社', '2012-12-1', '1', '212', '286000', '2012-12-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22910975-1_w.jpg', 'book_img/22910975-1_b.jpg', '63');
INSERT INTO `t_book` VALUES ('C23E6E8A6DB94E27B6E2ABD39DC21AF5', 'JavaScript:The Good Parts(影印版）', '克罗克福特', '28.00', '19.30', '6.9', '东南大学出版社', '2009-1-1', '1', '153', '181000', '2009-1-1', '16', '胶版纸', 'D45D96DA359A4FEAB3AB4DCF2157FC06', 'book_img/20412979-1_w.jpg', 'book_img/20412979-1_b.jpg', '95');
INSERT INTO `t_book` VALUES ('C3CF52B3ED2D4187A16754551488D733', 'Java从入门到精通（附光盘）', '魔乐科技', '59.00', '35.40', '6.0', '人民邮电出版社', '2010-4-1', '1', '519', '884000', '2010-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20810282-1_w_1.jpg', 'book_img/20810282-1_b.jpg', '29');
INSERT INTO `t_book` VALUES ('C86D3F6FACB449BEBD940D9307ED4A47', '编写高质量代码：改善Java程序的151个建议(从语法、程序设计和架构、工具和框架、编码风格、编程思想5个方面探讨编写高质量Java代码的技巧、禁忌和最佳实践)', '秦小波', '59.00', '40.70', '6.9', '机械工业出版社', '2012-1-1', '1', '303', '0', '2012-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22579686-1_w.jpg', 'book_img/22579686-1_b.jpg', '84');
INSERT INTO `t_book` VALUES ('CB0AB3654945411EA69F368D0EA91A00', 'JavaScript语言精粹（修订版）', '道格拉斯·克罗克福德', '49.00', '39.20', '8.0', '电子工业出版社', '2012-9-1', '1', '155', '258000', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22872884-1_w.jpg', 'book_img/22872884-1_b.jpg', '33');
INSERT INTO `t_book` VALUES ('CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '布鲁斯.艾克尔', '108.00', '74.50', '6.9', '机械工业出版社', '2007-6-1', '1', '880', '0', '2007-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9317290-1_w.jpg', 'book_img/9317290-1_b.jpg', '2');
INSERT INTO `t_book` VALUES ('D2ABA8B06C524632846F27C34568F3CE', 'Java 经典实例', '达尔文', '98.00', '67.60', '6.9', '中国电力出版社', '2009-2-1', '1', '784', '805000', '2009-2-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20500255-1_w.jpg', 'book_img/20500255-1_b.jpg', '62');
INSERT INTO `t_book` VALUES ('D8723405BA054C13B52357B8F6AEEC24', '深入理解Java虚拟机：JVM高级特性与最佳实践（第2版）', '周志明', '79.00', '54.50', '6.9', '机械工业出版社', '2013-6-1', '2', '433', '0', '2013-6-1', '16', '胶版纸', 'F4FBD087EB054CA1896093F172AC33D9', 'book_img/23259731-1_w.jpg', 'book_img/23259731-1_b.jpg', '14');
INSERT INTO `t_book` VALUES ('E27D4298E3FE402B9F917F40880854C2', '水浒传', '施耐庵', '59.00', '40.70', '6.9', '北京联合出版社', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', '113946AA10EF4DD29E46F6B0B85B1285', 'book_img/8F4FD18CC8F14EC8B2EB2533D6A467C0_shz1.jpg', 'book_img/20DC1C36A65D444AB1820DAA822648E8_shz.jpg', '124');
INSERT INTO `t_book` VALUES ('E5DE958F015249528FB62AEFB84468A4', '爱的教育', '(意)阿米琪斯著', '59.00', '40.70', '6.9', '江苏凤凰文艺出版社', '2017-10-28', '3', '374', '48700', '2017-10-28', '16', '胶版纸', '47ED6D6F601747AB8AFD6E14E6231DC5', 'book_img/9C1BDC34B16245C3B0B649C643CC3D46_adjy1.JPG', 'book_img/D126F4B08B2F4EC488B0A5719F2EF7CE_adjy.JPG', '122');
INSERT INTO `t_book` VALUES ('EFFB03103BEA46BEA8837947851B7C24', '红楼梦', '（清）曹雪芹', '59.00', '40.70', '6.9', '中国文联出版社', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', '113946AA10EF4DD29E46F6B0B85B1285', 'book_img/BC19D668ED694C92926A6C02FCA409F4_hlm1.JPG', 'book_img/A997EA8C686543D0BCBB4509EB6D7C5A_hlm.JPG', '115');
INSERT INTO `t_book` VALUES ('F693239BC3B3444C8538ABE7411BB38E', 'Java Web典型模块与项目实战大全（配光盘）', '常建功', '99.50', '68.70', '6.9', '清华大学出版社', '2011-1-1', '1', '922', '1473000', '2011-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20988080-1_w_1.jpg', 'book_img/20988080-1_b.jpg', '41');
INSERT INTO `t_book` VALUES ('F78C94641DB4475BBA1E72A07DF9B3AE', 'JAVA面向对象编程', '孙卫琴 ', '65.80', '45.40', '6.9', '电子工业出版社', '2006-7-1', '1', '625', '1030400', '2006-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9186890-1_w.jpg', 'book_img/9186890-1_b.jpg', '69');
INSERT INTO `t_book` VALUES ('F7E7773FDE7B49EE8EA2915F16AD66FC', '嫌疑人X的献身', '东野圭吾', '40.00', '28.00', '7.0', ' 南海出版公司', '2017-10-28', '1', '374', '48700', '2017-10-28', '16', '胶版纸', 'A9CFBED0F77746C5BD751F2502FAB2CD', 'book_img/12DD0E0A04DC4CC089C64D6F064E9571_xyr1.JPG', 'book_img/E62BBF81A3564C06A2F481D1B88A2FB4_xyr.JPG', '114');

-- ----------------------------
-- Table structure for t_cartitem
-- ----------------------------
DROP TABLE IF EXISTS `t_cartitem`;
CREATE TABLE `t_cartitem` (
  `cartItemId` char(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `bid` char(32) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  `orderBy` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cartItemId`),
  KEY `orderBy` (`orderBy`),
  KEY `FK_t_cartitem_t_user` (`uid`),
  KEY `FK_t_cartitem_t_book` (`bid`),
  CONSTRAINT `FK_t_cartitem_t_book` FOREIGN KEY (`bid`) REFERENCES `t_book` (`bid`),
  CONSTRAINT `FK_t_cartitem_t_user` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cartitem
-- ----------------------------
INSERT INTO `t_cartitem` VALUES ('C3E8D842793F415B981EC354C5DDD9FF', '1', 'CE01F15D435A4C51B0AD8202A318DCA7', '36CABB2DE9264DAA8809A7C823BE5C42', '1');

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `cid` char(32) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  `pid` char(32) DEFAULT NULL,
  `desk` varchar(100) DEFAULT NULL,
  `orderBy` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cname` (`cname`),
  KEY `FK_t_category_t_category` (`pid`),
  KEY `orderBy` (`orderBy`),
  CONSTRAINT `FK_t_category_t_category` FOREIGN KEY (`pid`) REFERENCES `t_category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '设计语言', null, '各种编程语言的书籍！', '1');
INSERT INTO `t_category` VALUES ('113946AA10EF4DD29E46F6B0B85B1285', '中国名著', '1550BA26F50246F19DC33DB83AFD4FA2', '四大名著', '46');
INSERT INTO `t_category` VALUES ('1550BA26F50246F19DC33DB83AFD4FA2', '世界名著', null, '著名书籍', '45');
INSERT INTO `t_category` VALUES ('2', '编程入门级图书', null, '迅速带你进入编程世界！', '2');
INSERT INTO `t_category` VALUES ('47ED6D6F601747AB8AFD6E14E6231DC5', '外国文学', '1550BA26F50246F19DC33DB83AFD4FA2', '外国文学作品', '47');
INSERT INTO `t_category` VALUES ('5', '数据库', null, '数据库', '5');
INSERT INTO `t_category` VALUES ('5F79D0D246AD4216AC04E9C5FAB3199E', 'Java Javascript', '1', 'Java Javascript分类', '10');
INSERT INTO `t_category` VALUES ('6', '畅销小说', null, '最近热销的小说!', '6');
INSERT INTO `t_category` VALUES ('84ECE401C2904DBEA560D04A581A66D9', 'HTML ', '1', 'HTML 分类', '13');
INSERT INTO `t_category` VALUES ('922E6E2DB04143D39C9DDB26365B3EE8', 'C语言 C++ C# Java', '1', 'C语言 C++（面向对象） C# Java', '12');
INSERT INTO `t_category` VALUES ('A9CFBED0F77746C5BD751F2502FAB2CD', '现代小说', '6', '悬疑，推理，现代小说', '35');
INSERT INTO `t_category` VALUES ('C3F9FAAF4EA64857ACFAB0D9C8D0E446', 'PHP', '1', 'PHP分类', '14');
INSERT INTO `t_category` VALUES ('C4DD8CA232864B31A367EE135D86382C', '计算机初级入门', '2', '计算机初级入门分类', '17');
INSERT INTO `t_category` VALUES ('D45D96DA359A4FEAB3AB4DCF2157FC06', 'JSP', '1', 'JSP分类', '11');
INSERT INTO `t_category` VALUES ('F4FBD087EB054CA1896093F172AC33D9', '数据仓库与数据挖掘', '5', '数据仓库与数据挖掘分类', '30');
INSERT INTO `t_category` VALUES ('F5C091B3967442A2B35EFEFC4EF8746F', '微软Office', '2', '微软Office分类', '16');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `oid` char(32) NOT NULL,
  `ordertime` char(19) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_t_order_t_user` (`uid`),
  CONSTRAINT `FK_t_order_t_user` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1D6EA96C7587492F8BE9CB358BAD4F06', '2019-09-23 16:30:35', '95.30', '4', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('20F15DA470A14CFA99720AFC1C9A152D', '2019-09-26 08:41:57', '74.50', '3', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('3DA39D1C215340B6972A082F90AB5D42', '2019-09-25 09:58:34', '40.70', '2', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('4747F575AEA141DFAFE1B0B79CA488F3', '2017-12-28 18:13:13', '124.00', '4', '山东省 烟台市 芝罘区 鲁东大学北区  张三同学收', '36CABB2DE9264DAA8809A7C823BE5C42');
INSERT INTO `t_order` VALUES ('6B435004498545328EF406F01B7836FC', '2019-09-24 20:01:33', '81.40', '4', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('6C450C2D0DC74A5B84C4FC3486310313', '2019-09-23 16:47:20', '82.10', '4', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('830CA57878DB4B2B8DB17989B08363E6', '2019-09-23 16:58:21', '95.30', '4', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('885752264AEB4C96B0C8366E2D239135', '2019-09-23 16:43:51', '74.50', '4', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('8F7C6B49D1E8445398A42A1F6CFA693F', '2017-12-28 18:24:27', '67.60', '4', '山东省 烟台市 芝罘区 鲁东大学北区  纪英磊同学收', '389958042EE34E00A34E0FBAC3A3CD64');
INSERT INTO `t_order` VALUES ('93820299932947B4859A5DB039B65A43', '2017-12-28 18:11:44', '121.90', '3', '山东省 烟台市 芝罘区 鲁东大学北区  纪英磊同学收', '36CABB2DE9264DAA8809A7C823BE5C42');
INSERT INTO `t_order` VALUES ('9CFDD870ABAA4A8A90D6C5CEE52BECDF', '2019-09-23 16:26:19', '41.40', '3', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('B931FC8C5AB244D0844501231BFFCCA5', '2019-09-24 20:57:15', '55.10', '3', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('C545BD04AF9741C5ADD21DB672607F9F', '2019-09-24 20:50:08', '48.20', '5', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('D516F90DBD6443D195B15FBF3FBF0CC1', '2019-09-24 20:49:19', '67.60', '3', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('D650CB3412554A728281231525BCD28E', '2019-09-23 16:37:02', '68.90', '4', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('E360310345CA46FFB4458EDE714E5012', '2019-09-23 16:18:59', '95.30', '3', '福建省 厦门市 思明区 厦门大学 Zico同学收', '9DC5F59ADB214A8591C263E2D1AB9578');
INSERT INTO `t_order` VALUES ('FF00453DCAA448618E94DA4CDCE9C3DE', '2017-12-28 18:23:59', '68.30', '3', '山东省 烟台市 芝罘区 鲁东大学北区  张三同学收', '389958042EE34E00A34E0FBAC3A3CD64');

-- ----------------------------
-- Table structure for t_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `t_orderitem`;
CREATE TABLE `t_orderitem` (
  `orderItemId` char(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` decimal(8,2) DEFAULT NULL,
  `bid` char(32) DEFAULT NULL,
  `bname` varchar(200) DEFAULT NULL,
  `currPrice` decimal(8,2) DEFAULT NULL,
  `image_b` varchar(100) DEFAULT NULL,
  `oid` char(32) DEFAULT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `FK_t_orderitem_t_order` (`oid`),
  CONSTRAINT `FK_t_orderitem_t_order` FOREIGN KEY (`oid`) REFERENCES `t_order` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orderitem
-- ----------------------------
INSERT INTO `t_orderitem` VALUES ('1CF3AE7D464A4B27A11A9C55CF06AA81', '1', '68.90', '4C3331CAD5A5453787A94B8D7CCEAA29', 'Java Web整合开发王者归来（JSP+Servlet+Struts+Hibernate+Spring）（配光盘', '68.90', 'book_img/20756351-1_b_1.jpg', 'D650CB3412554A728281231525BCD28E');
INSERT INTO `t_orderitem` VALUES ('4E144927A6524242BF7C710DED476330', '1', '74.50', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', '885752264AEB4C96B0C8366E2D239135');
INSERT INTO `t_orderitem` VALUES ('5365B5EF9B4C4721B749F110902232AA', '1', '67.60', '0EE8A0AE69154287A378FB110FF2C780', 'Java核心技术：卷Ⅰ基础知识（原书第8版）', '67.60', 'book_img/20285763-1_b.jpg', 'D516F90DBD6443D195B15FBF3FBF0CC1');
INSERT INTO `t_orderitem` VALUES ('5E8F522D65544F3696CBD7F05CC9BAC4', '1', '55.10', '819FF56E4423462394E6F83882F78975', '学通Java Web的24堂课（配光盘）（软件开发羊皮卷）', '55.10', 'book_img/21118835-1_b.jpg', 'B931FC8C5AB244D0844501231BFFCCA5');
INSERT INTO `t_orderitem` VALUES ('5F46F91B25C84184A772EBC78BBC1003', '1', '47.40', '95AACC68D64D4D67B1E33E9EAC22B885', 'Head First Java（中文版）（JAVA经典畅销书 生动有趣 轻松学好JAVA）', '47.40', 'book_img/9265169-1_b.jpg', '93820299932947B4859A5DB039B65A43');
INSERT INTO `t_orderitem` VALUES ('905D01D27F294AA8A3996544A477F115', '1', '95.30', '48BBFBFC07074ADE8CC906A45BE5D9A6', 'JavaScript权威指南（第6版）（淘宝前端团队倾情翻译！经典权威的JavaScript犀牛书！第6版特别涵盖了HTML5和ECMAScript5！）（经典巨著，当当独家首发）', '95.30', 'book_img/22722790-1_b.jpg', '1D6EA96C7587492F8BE9CB358BAD4F06');
INSERT INTO `t_orderitem` VALUES ('9AE8AC04EC774CD0BDF57574C9E20C1A', '1', '82.10', '3DD187217BF44A99B86DD18A4DC628BA', 'Java核心技术 卷1 基础知识（原书第9版）', '82.10', 'book_img/23362142-1_b.jpg', '6C450C2D0DC74A5B84C4FC3486310313');
INSERT INTO `t_orderitem` VALUES ('9F4FD9E156734F7986600DE77566FB5D', '2', '81.40', '9201DECE2DD44CD8B1B9531CA43BA732', '失乐园', '40.70', 'book_img/5F4F0D458E3E461B8FA9918628F1695C_0001-b.jpg', '6B435004498545328EF406F01B7836FC');
INSERT INTO `t_orderitem` VALUES ('9F9D1E93C3C64431BBA6857A2D68DF27', '1', '95.30', '48BBFBFC07074ADE8CC906A45BE5D9A6', 'JavaScript权威指南（第6版）（淘宝前端团队倾情翻译！经典权威的JavaScript犀牛书！第6版特别涵盖了HTML5和ECMAScript5！）（经典巨著，当当独家首发）', '95.30', 'book_img/22722790-1_b.jpg', '830CA57878DB4B2B8DB17989B08363E6');
INSERT INTO `t_orderitem` VALUES ('A2F9FA8E7F424DF59EBF561B60A6F907', '1', '74.50', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', '20F15DA470A14CFA99720AFC1C9A152D');
INSERT INTO `t_orderitem` VALUES ('AA3BE592383647D18AE5F8918C52BB6A', '1', '74.50', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', '93820299932947B4859A5DB039B65A43');
INSERT INTO `t_orderitem` VALUES ('B14DF71CC62A422B88987BAD220926C5', '1', '40.70', '9201DECE2DD44CD8B1B9531CA43BA732', '失乐园', '40.70', 'book_img/5F4F0D458E3E461B8FA9918628F1695C_0001-b.jpg', '3DA39D1C215340B6972A082F90AB5D42');
INSERT INTO `t_orderitem` VALUES ('BB26BE5BEE4F424F9B4570D8D2116444', '1', '68.30', '8E16D59BA4C34374A68029AE877613C4', '轻量级Java EE企业应用实战（第3版）：Struts 2＋Spring 3＋Hibernate整合开发(含CD光盘1张)', '68.30', 'book_img/22685703-1_b.jpg', 'FF00453DCAA448618E94DA4CDCE9C3DE');
INSERT INTO `t_orderitem` VALUES ('CA9B5654DE4B449CBA910F5334068082', '1', '67.60', 'A7EFD99367C9434682A790635D3C5FDF', 'Java Web技术整合应用与项目实战（JSP+Servlet+Struts2+Hibernate+Spring3）', '67.60', 'book_img/23266270-1_b.jpg', '8F7C6B49D1E8445398A42A1F6CFA693F');
INSERT INTO `t_orderitem` VALUES ('DE7251A7EFD440DB886A013B02488F45', '1', '95.30', '48BBFBFC07074ADE8CC906A45BE5D9A6', 'JavaScript权威指南（第6版）（淘宝前端团队倾情翻译！经典权威的JavaScript犀牛书！第6版特别涵盖了HTML5和ECMAScript5！）（经典巨著，当当独家首发）', '95.30', 'book_img/22722790-1_b.jpg', 'E360310345CA46FFB4458EDE714E5012');
INSERT INTO `t_orderitem` VALUES ('E12FEDC9F5804B81B36F6AD5C709C6A2', '1', '55.10', 'CD913617EE964D0DBAF20C60076D32FB', '名师讲坛——Java开发实战经典（配光盘）（60小时全真课堂培训，视频超级给力！790项实例及分析，北京魔乐科技培训中心Java全部精华）', '55.10', 'book_img/20637368-1_b_2.jpg', '4747F575AEA141DFAFE1B0B79CA488F3');
INSERT INTO `t_orderitem` VALUES ('E3427851FD3A48B8A9F031C97279FB05', '1', '48.20', '4BF6D97DD18A4B77B8DED9B057577F8F', 'Java Web从入门到精通（附光盘1张）（连续8月Java类全国零售排行前2名，27小时视频，951个经典实例、369项面试真题、596项测试史上最全资源库）', '48.20', 'book_img/22862056-1_b.jpg', 'C545BD04AF9741C5ADD21DB672607F9F');
INSERT INTO `t_orderitem` VALUES ('E58996FF8E3C42D0AC9423615B75E5DD', '1', '68.90', '4C3331CAD5A5453787A94B8D7CCEAA29', 'Java Web整合开发王者归来（JSP+Servlet+Struts+Hibernate+Spring）（配光盘', '68.90', 'book_img/20756351-1_b_1.jpg', '4747F575AEA141DFAFE1B0B79CA488F3');
INSERT INTO `t_orderitem` VALUES ('FF7C0AC7901749EABC2D493B9531EAA3', '1', '41.40', '52B0EDFF966E4A058BDA5B18EEC698C4', '亮剑Java Web项目开发案例导航(含DVD光盘1张)', '41.40', 'book_img/22623766-1_b.jpg', '9CFDD870ABAA4A8A90D6C5CEE52BECDF');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` char(32) NOT NULL,
  `loginname` varchar(50) DEFAULT NULL,
  `loginpass` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `loginname` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('36CABB2DE9264DAA8809A7C823BE5C42', '禹智皓', '1234567', '1348466756@qq.com', '1');
INSERT INTO `t_user` VALUES ('389958042EE34E00A34E0FBAC3A3CD64', '郑基石', '123', '1987993545@qq.com', '1');
INSERT INTO `t_user` VALUES ('9DC5F59ADB214A8591C263E2D1AB9578', '金知元', '123', '1348466788@qq.com', '1');
INSERT INTO `t_user` VALUES ('C8F68A0E9A0046E09A1CC0F158FF6F62', '朴宰范', '123456', '1348462788@qq.com', '1');
