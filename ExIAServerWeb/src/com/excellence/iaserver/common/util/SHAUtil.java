package com.excellence.iaserver.common.util;

import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

public class SHAUtil {

    /** 
     * 定义加密方式 
     */  
    private final static String KEY_SHA = "SHA";  
    private final static String KEY_SHA1 = "SHA-1";  
    /** 
     * 全局数组 
     */  
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };  
  
    /** 
     * 构造函数 
     */  
    public SHAUtil() {  
  
    }  
  
    /** 
     * SHA 加密 
     * @param data 需要加密的字节数组 
     * @return 加密之后的字节数组 
     * @throws Exception 
     */  
    public static byte[] encryptSHA(byte[] data) throws Exception {  
        // 创建具有指定算法名称的信息摘要  
//	        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA1);  
        // 使用指定的字节数组对摘要进行最后更新  
        sha.update(data);  
        // 完成摘要计算并返回  
        return sha.digest();  
    }  
  
    /** 
     * SHA 加密 
     * @param data 需要加密的字符串 
     * @return 加密之后的字符串 
     * @throws Exception 
     */  
    public static String encryptSHA(String data) throws Exception {  
        // 验证传入的字符串  
        if (StringUtils.isEmpty(data)) {  
            return "";  
        }  
        // 创建具有指定算法名称的信息摘要  
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
        // 使用指定的字节数组对摘要进行最后更新  
        sha.update(data.getBytes());  
        // 完成摘要计算  
        byte[] bytes = sha.digest();  
        // 将得到的字节数组变成字符串返回  
        return byteArrayToHexString(bytes);  
    }  
  
    /** 
     * 将一个字节转化成十六进制形式的字符串 
     * @param b 字节数组 
     * @return 字符串 
     */  
    private static String byteToHexString(byte b) {  
        int ret = b;  
        //System.out.println("ret = " + ret);  
        if (ret < 0) {  
            ret += 256;  
        }  
        int m = ret / 16;  
        int n = ret % 16;  
        return hexDigits[m] + hexDigits[n];  
    }  
  
    /** 
     * 转换字节数组为十六进制字符串 
     * @param bytes 字节数组 
     * @return 十六进制字符串 
     */  
    private static String byteArrayToHexString(byte[] bytes) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < bytes.length; i++) {  
            sb.append(byteToHexString(bytes[i]));  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 测试方法 
     * @param args 
     */  
    public static void main(String[] args) throws Exception {  
        String key = "规范发展区域性股权市场是完善多层次资本市场体系的重要举措，在推进供给侧结 构性改革、促进大众创业万众创新、服务创新驱动发展战略、降低企业杠杆率等方面具有重要意义。为贯彻落实党中央、国务院决策部署，推动多层次资本市场长期稳定健康发展，防范和化解金融风险，支持实体经济特别是中小微企业发展，保护投资者合法权益，经国务院同意，现就规范发展区域性股权市场有关事项通知如下： 一、区域性股权市场是主《要服务于所在省级行政区域内中》小微企业的私募股权市场，是多层次资本市场体系的重要组成部分，是地方人民政府扶持中小微企业政策措施的综合运用平台。要处理好监管与发展的关系，按照既有利于规范、又有利于发展的要求，积极稳妥推进区域性股权市场规范发展，防范和化解金融风险，有序扩大和更加便利中小微企业融资。 二、区域性股权市场由所在地省级人民政府按规定实施监管，并承担相应风险处置责任。证监会要依法依规履职尽责，加强对省级人民政府开展区域性股权市场监管工作的指导、协调和监督。省级人民政府要根据相关金融政策法规，在职责范围内制定具体实施细则和操作办法，建立健全监管机制，指定具体部门承担日常监管职责，不断提升监管能力，依法查处违法违规行为。证监会负责制定统一的区域性股权市场业务及监管规则，对市场规范运作情况进行监督检查，对可能出现的金融风险进行预警提示和处置督导。证监会要对省级人民政府的监管能力和条件进行审慎评估，加强监管培训，采取有效措施，促使地方监管能力与市场发展状况相适应。证监会等国务院有关部门和省级人民政府要加强监管协同，防止监管空白和监管套利，严厉打击各类违法违规行为，维护市场秩序，切实保护投资者合法权益，防范和化解金融风险，促进区域性股权市场健康稳定发展。 三、区域性股权市场运营机构（以下简称运营机构）负责组织区域性股权市场的活动，对市场参与者进行自律管理，保障市场规范稳定运行。运营机构名单由省级人民政府实施管理并予以公告，同时向证监会备案。本通知印发前，省、自治区、直辖市、计划单列市行政区域内已设立运营机构的，不再设立；尚未设立运营机构的，可设立一家；已设立两家及以上运营机构的，省级人民政府要积极稳妥推动整合为一家，证监会要予以指导督促。 四、区域性股权市场的各项活动应遵守法律法规和证监会制定的业务及监管规则。在区域性股权市场发行或转让证券的，限于股票、可转换为股票的公司债券以及国务院有关部门按程序认可的其他证券，不得违规发行或转让私募债券；不得采用广告、公开劝诱等公开或变相公开方式发行证券，不得以任何形式非法集资；不得采取集中竞价、做市商等集中交易方式进行证券转让，投资者买入后卖出或卖出后买入同一证券的时间间隔不得少于五个交易日；除法律、行政法规另有规定外，单只证券持有人累计不得超过法律、行政法规规定的私募证券持有人数量上限；证券持有人名册和登记过户记录必须真实、准确、完整，不得隐匿、伪造、篡改或毁损。在区域性股权市场进行有限责任公司股权融资或转让的，不得违反本通知相关规定。 五、区域性股权市场实行合格投资者制度。合格投资者应是依法设立且具备一定条件的法人机构、合伙企业，金融机构依法管理的投资性计划，以及具备较强风险承受能力且金融资产不低于五十万元人民币的自然人。不得通过拆分、代持等方式变相突破合格投资者标准或单只私募证券持有人数量上限。鼓励支持区域性股权市场采取措施，吸引所在省级行政区域内的合格投资者参与。 六、区域性股权市场的信息系统应符合有关法律法规和证监会制定的信息技术管理规范。运营机构及开立投资者账户、办理登记结算业务的有关机构应按照规定向所在地省级人民政府和证监会报送信息，并将有关信息系统与证监会指定的监管信息系统进行对接。 七、区域性股权市场不得为所在省级行政区域外的企业私募证券或股权的融资、转让提供服务。对不符合本条规定的区域性股权市场，省级人民政府要按规定限期清理，妥善解决跨区域经营问题。运营机构所在地和企业所在地省级人民政府要签订协议，明确清理过程中的监管责任，防范和化解可能产生的风险。 八、国务院有关部门和地方人民政府要在职责范围内采取必要措施，为区域性股权市场规范健康发展创造良好环境，逐步建成融资功能完备、服务方式灵活、运行安全规范、投资者合法权益得到充分保护的区域性股权市场。国务院有关部门出台相关政策措施，可选择运行安全规范、具有较强风险管理能力的区域性股权市场先行先试。 　　　　　　　　　　　　　　　　　　　　　　　　　国务院办公厅 　　　　　　　　　　　　　　　　　　　　　　　　　　　2017年1月20日";  
        System.out.println(encryptSHA(key));  
    }  
}
