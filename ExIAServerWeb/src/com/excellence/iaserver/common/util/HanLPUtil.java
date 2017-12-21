package com.excellence.iaserver.common.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

public class HanLPUtil {
	public static void main(String[] args) {
		Date a = new Date();
        System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……\n");
        //第一次运行会有文件找不到的错误但不影响运行，缓存完成后就不会再有了
        System.out.println("标准分词：");
        System.out.println(getKeyWordsAndRank("简政放权的要求已经提出来有一段时间了，目前正在从各个角度、各个层次在全国各省市自治区稳步推进，成效显著。以前备受老百姓诟病的“奇葩证明”越来越少了，也不再是媒体新闻聚焦的热点了；随着“权力清单”“责任清单”的公布，“清单之外无权力”的意识正在成为越来越多领导干部的为官自觉，手乱伸，伸太长的现象也越来越少。  　　“春江水暖鸭先知”，推动简政放权的是中央以及地方政府，但是对简政放权感受最迅速，最明显的，却是全国成千上万的企业和个人。企业和公民个人在工作与生活中最大的感受就是，现在办事情比以前程序更少了，成本更低了，效率反而更高了。而从一个更高的高度来看，因为简政放权效果初显，经济活力与社会活力都得到了有效的释放，呈现出一片活力四射与欣欣向荣的局面。  　　今年6月中旬，湖南省某房地产开发有限公司从市住建局领到了建筑工程竣工联合验收意见书，用时15个工作日。这让该公司办公室工作人员深感“震惊”。“要在以前，需跑10多个部门，一家家递材料，没有好几个月别想办下来。”从过去的好几个月到现在的半个月，在风云变幻、竞争激烈的市场大潮中，这就意味着先机，意味着契机，意味着企业经营管理成本的降低，运转效率的提高，市场竞争能力的增强。  　　这种前后变化的原因何在？就在于当地围绕着简政放权为核心的行政变革。2015年，湖南省出台了推进简政放权、放管结合、转变政府职能工作方案，统筹推动行政审批、投资审批、职业资格、收费管理、商事制度、教科文卫体等领域改革。其中在减权环节，省本级行政审批事项从2000年的2543项精减到行政许可304项、非行政许可审批事项全部取消，减少88%；市级平均130项，减少52%；县级平均185项，减少56%。  　　变革前后数据上的鲜明对比，最终势必反映在经济实体上。湖南省通过简政放权，除了直接为企业减负约30亿元之外，由于行政桎梏的减少，市场活力的增加，仅仅在2016年上半年，湖南新增市场主体、注册资本分别增长28.6%和53.1%。通过建设创新创业园区，引进“双创”企业3840家。直到这个时候，一些人才真正见识到了简政放权所能够释放的巨大活力所在，才把之前狐疑的目光换为坚定的支持。  　　简政放权的威力当然不会只在湖南省一地发挥作用。今年以来，记者从天津市政府有关部门了解到这样一组数据：天津市行政审批事项已从2013年的394项减少到2016年的274项。同时，今年上半年，天津新设立企业数48234家，同比增加46%，总投资额3620亿元，同比增加77%。数据对比告诉我们一个这样的事实，那就是行政审批“大瘦身”，带来的是市场活力的大爆发，是经济前景的无限光明。通过这一系列的改革，最终实现了给市场让位，为企业松绑，让群众舒心，激发了市场活力和社会创造力的目的，由此带动了新产业、新业态、新模式的蓬勃发展，助力中国经济新动能的加快培育。  　　相比于公民个人，通过简政放权获益最多的显然还是企业。今年以来，广州某科技有限公司的进境维修业务开展得如火如荼，今年进境维修34架飞机整机，几乎与去年全年持平。除了国内飞机维修企业的实力增强、声誉传播更广之外，飞机维修进境流程的简化和通关成本的降低是这项业务蓬勃发展的重要原因，而这同样是政府大力推行简政放权的结果。比如以前飞机进境维修办理担保手续需要经过三级审批，耗费时间长，花费精力多，降低了企业的工作效率，还提高了企业的管理成本。而经过简政放权，审批流程简化了，企业当天交上担保资料，第二天就能审批下来。时间短、花费低，结果就是更多的业务主动愿意找上门来，企业经营越来红火。  　　无数的实践已经证明，简政放权的方向没有错，简政放权的步子还可以在保持稳健的前提下迈得更大些。",20,false));
        Date b = new Date();
        System.out.println(b.getTime()-a.getTime());
        System.out.println("\n");

        List<Term> termList = NLPTokenizer.segment("继续推进简政放权释放经济社会活力金华信息科技有限公司");
        System.out.println("NLP分词：");
        System.out.println(termList);
        System.out.println("\n");

    }
	
	private static List<String> EXKEWORDS = new ArrayList<String>();
	
	static{
		String oaPath = System.getProperty("oapath");
		String allFilePath = oaPath + File.separator+"exiaserver"+File.separator+"dict"+File.separator+"all.txt";
		try {
			InputStream in = new FileInputStream(allFilePath);
			
			String str = IOUtils.toString(in,"utf-8");
			if(StringUtils.isNotEmpty(str)){
				String[] split = str.split("\n");
				for(String s:split){
					if(s.length()<=20){
						EXKEWORDS.add(s);
					}
				}
			}
		
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据主题词词库过滤
	 * @param words
	 * @return
	 */
	public static List filterWords(List words){
		List result = new ArrayList();
		if(words==null || words.size()==0){
			return null;
		}
		
		if(EXKEWORDS !=null && EXKEWORDS.size()>0){
			for(int i=0;i<words.size();i++){
				String word = "";
				if(words.get(i) instanceof Term){
					Term s = (Term)words.get(i);
					word = s.word;
				}else{
					word = (String)words.get(i);
				}
				
				if(EXKEWORDS.contains(word)){
					result.add(words.get(i));
				}
			}
		}
		return result;
	}

	/**
	 * HanLP标准分词
	 * @param content 文本内容
	 * @param res 是否根据主题词库过滤
	 * @param tagPart 是否标注词性
	 * @param patten 分词模式默认为1		1--标准分词，2--索引分词，3--全分词，4--极速分词，5--CRF分词
	 * @return
	 */
	public static List getWords(String content, boolean res, boolean tagPart, int patten){
		List<Term> segment = new ArrayList<Term>();
		if(patten==1){
			segment = HanLP.segment(content);
		}else if(patten==2){
			segment = IndexTokenizer.segment(content);
		}else if(patten==3){
			// DoubleArrayTrie分词
			final List<Term> termList = new ArrayList<Term>();
	        final char[] charArray = content.toCharArray();
	        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
	        {
	            @Override
	            public void hit(int begin, int end, CoreDictionary.Attribute value)
	            {
	            	Term term = new Term(new String(charArray, begin, end - begin), value.nature[0]);
	            	termList.add(term);
	            }
	        });
			
	        segment.addAll(termList);
		}else if(patten==4){
			segment = SpeedTokenizer.segment(content);
		}else if(patten==5){
			Segment segm = new CRFSegment();
			segm.enablePartOfSpeechTagging(true);
			segment = segm.seg(content);
		}
		
		if(res){
			segment = filterWords(segment);
		}
		
		if(tagPart){
			return segment;
		}else{
			List words = new ArrayList();
			for(Term term:segment){
				words.add(term.word);
			}
			return words;
		}
	}
	
	/**
	 * 人名识别
	 * @param text
	 * @return
	 */
	public static List recPersonName(String text){
		List<Term> segment = HanLP.segment(text);
		List<Term> result = new ArrayList<Term>();
		
		if(segment!=null && segment.size()>0){
			for(Term term:segment){
				if(term.nature.equals(Nature.nr) || term.nature.equals(Nature.nt)){
					result.add(term);
				}
			}
		}
		return result;
	}
	
	/**
	 * HanLP全分词
	 * @param content 文本内容
	 * @param res 是否根据主题词库过滤
	 * @param tagPart 是否标注词性
	 * @return
	 */
	public static List getAllWords(String content, boolean res, boolean tagPart){
		// DoubleArrayTrie分词
		List<Term> segment = new ArrayList<Term>();
		final List<Term> termList = new ArrayList<Term>();
        final char[] charArray = content.toCharArray();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
        {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value)
            {
            	Term term = new Term(new String(charArray, begin, end - begin), value.nature[0]);
            	termList.add(term);
            }
        });
		
        segment.addAll(termList);
//		List<Term> segment = HanLP.segment(content);
		
		if(res){
			segment = filterWords(segment);
		}
		
		if(tagPart){
			return segment;
		}else{
			List words = new ArrayList();
			for(Term term:segment){
				words.add(term.word);
			}
			return words;
		}
	}

    /**
     * 关键字提取
     * @param content 正文内容
     * @param wordCount 关键词数量
     * @param res 是否根据主题词库过滤
     * @return
     */
    public static List getKeyWords(String content, int wordCount, boolean res) {
        List<String> keywordList = HanLP.extractKeyword(content, wordCount);
        
        if(res){
        	keywordList = filterWords(keywordList);
		}
        return keywordList;
    }
    
    /**
     * 关键字提取并返回对应的权重
     * @param content 文本内容
     * @param wordCount 数量
     * @param res 是否过滤主题词
     * @return
     */
    public static Map<String, Float> getKeyWordsAndRank(String content,int wordCount,boolean res){
    	Map<String, Float> termAndRank = TextRankKeyword.getTermAndRank(content, wordCount);
    	
    	Set<Map.Entry<String, Float>> entrySet = termAndRank.entrySet();
        List<String> keywordList = new ArrayList<String>(entrySet.size());
        int i=0;
        double m = 0;
        for (Map.Entry<String, Float> entry : entrySet)
        {
        	if(i==0){
        		m = 1/entry.getValue();
        		entry.setValue((float) 1);
        	}else{
        		entry.setValue((float) (entry.getValue()*m));
        	}
        	
        	keywordList.add(entry.getKey());
        	i++;
        }
       
        if(res){
        	keywordList = filterWords(keywordList);
        }
        
        Map<String, Float> result = new HashMap<String, Float>();
        for(String kw:keywordList){
        	result.put(kw, termAndRank.get(kw));
        }
        return result;
    }

    /**
     * 自动提取摘要
     * @param document 文档内容
     * @param summaryCount  摘要数量
     * @return
     */
    public static String getSummary(String document, int summaryCount) {
//        String document = "简政放权的要求已经提出来有一段时间了，目前正在从各个角度、各个层次在全国各省市自治区稳步推进，成效显著。以前备受老百姓诟病的“奇葩证明”越来越少了，也不再是媒体新闻聚焦的热点了；随着“权力清单”“责任清单”的公布，“清单之外无权力”的意识正在成为越来越多领导干部的为官自觉，手乱伸，伸太长的现象也越来越少。  　　“春江水暖鸭先知”，推动简政放权的是中央以及地方政府，但是对简政放权感受最迅速，最明显的，却是全国成千上万的企业和个人。企业和公民个人在工作与生活中最大的感受就是，现在办事情比以前程序更少了，成本更低了，效率反而更高了。而从一个更高的高度来看，因为简政放权效果初显，经济活力与社会活力都得到了有效的释放，呈现出一片活力四射与欣欣向荣的局面。  　　今年6月中旬，湖南省某房地产开发有限公司从市住建局领到了建筑工程竣工联合验收意见书，用时15个工作日。这让该公司办公室工作人员深感“震惊”。“要在以前，需跑10多个部门，一家家递材料，没有好几个月别想办下来。”从过去的好几个月到现在的半个月，在风云变幻、竞争激烈的市场大潮中，这就意味着先机，意味着契机，意味着企业经营管理成本的降低，运转效率的提高，市场竞争能力的增强。  　　这种前后变化的原因何在？就在于当地围绕着简政放权为核心的行政变革。2015年，湖南省出台了推进简政放权、放管结合、转变政府职能工作方案，统筹推动行政审批、投资审批、职业资格、收费管理、商事制度、教科文卫体等领域改革。其中在减权环节，省本级行政审批事项从2000年的2543项精减到行政许可304项、非行政许可审批事项全部取消，减少88%；市级平均130项，减少52%；县级平均185项，减少56%。  　　变革前后数据上的鲜明对比，最终势必反映在经济实体上。湖南省通过简政放权，除了直接为企业减负约30亿元之外，由于行政桎梏的减少，市场活力的增加，仅仅在2016年上半年，湖南新增市场主体、注册资本分别增长28.6%和53.1%。通过建设创新创业园区，引进“双创”企业3840家。直到这个时候，一些人才真正见识到了简政放权所能够释放的巨大活力所在，才把之前狐疑的目光换为坚定的支持。  　　简政放权的威力当然不会只在湖南省一地发挥作用。今年以来，记者从天津市政府有关部门了解到这样一组数据：天津市行政审批事项已从2013年的394项减少到2016年的274项。同时，今年上半年，天津新设立企业数48234家，同比增加46%，总投资额3620亿元，同比增加77%。数据对比告诉我们一个这样的事实，那就是行政审批“大瘦身”，带来的是市场活力的大爆发，是经济前景的无限光明。通过这一系列的改革，最终实现了给市场让位，为企业松绑，让群众舒心，激发了市场活力和社会创造力的目的，由此带动了新产业、新业态、新模式的蓬勃发展，助力中国经济新动能的加快培育。  　　相比于公民个人，通过简政放权获益最多的显然还是企业。今年以来，广州某科技有限公司的进境维修业务开展得如火如荼，今年进境维修34架飞机整机，几乎与去年全年持平。除了国内飞机维修企业的实力增强、声誉传播更广之外，飞机维修进境流程的简化和通关成本的降低是这项业务蓬勃发展的重要原因，而这同样是政府大力推行简政放权的结果。比如以前飞机进境维修办理担保手续需要经过三级审批，耗费时间长，花费精力多，降低了企业的工作效率，还提高了企业的管理成本。而经过简政放权，审批流程简化了，企业当天交上担保资料，第二天就能审批下来。时间短、花费低，结果就是更多的业务主动愿意找上门来，企业经营越来红火。  　　无数的实践已经证明，简政放权的方向没有错，简政放权的步子还可以在保持稳健的前提下迈得更大些。";
        List<String> sentenceList = HanLP.extractSummary(document, summaryCount);
        
        String summary="";
        if(sentenceList!=null && sentenceList.size()>0){
        	for(String s:sentenceList){
        		summary+=s+"。";
        	}
        }
        return summary;
    }

}
