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
        System.out.println("�״α�������ʱ��HanLP���Զ������ʵ仺�棬���Ժ򡭡�\n");
        //��һ�����л����ļ��Ҳ����Ĵ��󵫲�Ӱ�����У�������ɺ�Ͳ���������
        System.out.println("��׼�ִʣ�");
        System.out.println(getKeyWordsAndRank("������Ȩ��Ҫ���Ѿ��������һ��ʱ���ˣ�Ŀǰ���ڴӸ����Ƕȡ����������ȫ����ʡ���������Ȳ��ƽ�����Ч��������ǰ�����ϰ���ڸ���ġ�����֤����Խ��Խ���ˣ�Ҳ������ý�����ž۽����ȵ��ˣ����š�Ȩ���嵥���������嵥���Ĺ��������嵥֮����Ȩ��������ʶ���ڳ�ΪԽ��Խ���쵼�ɲ���Ϊ���Ծ��������죬��̫��������ҲԽ��Խ�١�  ����������ˮůѼ��֪�����ƶ�������Ȩ���������Լ��ط����������ǶԼ�����Ȩ������Ѹ�٣������Եģ�ȴ��ȫ����ǧ�������ҵ�͸��ˡ���ҵ�͹�������ڹ��������������ĸ��ܾ��ǣ����ڰ��������ǰ��������ˣ��ɱ������ˣ�Ч�ʷ��������ˡ�����һ�����ߵĸ߶���������Ϊ������ȨЧ�����ԣ����û��������������õ�����Ч���ͷţ����ֳ�һƬ�����������������ٵľ��档  ��������6����Ѯ������ʡĳ���ز��������޹�˾����ס�����쵽�˽������̿���������������飬��ʱ15�������ա����øù�˾�칫�ҹ�����Ա��С��𾪡�����Ҫ����ǰ������10������ţ�һ�Ҽҵݲ��ϣ�û�кü����±�������������ӹ�ȥ�ĺü����µ����ڵİ���£��ڷ��Ʊ�á��������ҵ��г����У������ζ���Ȼ�����ζ����������ζ����ҵ��Ӫ����ɱ��Ľ��ͣ���תЧ�ʵ���ߣ��г�������������ǿ��  ��������ǰ��仯��ԭ����ڣ������ڵ���Χ���ż�����ȨΪ���ĵ�������2015�꣬����ʡ��̨���ƽ�������Ȩ���Źܽ�ϡ�ת������ְ�ܹ���������ͳ���ƶ�����������Ͷ��������ְҵ�ʸ��շѹ��������ƶȡ��̿������������ĸ�����ڼ�Ȩ���ڣ�ʡ�����������������2000���2543������������304������������������ȫ��ȡ��������88%���м�ƽ��130�����52%���ؼ�ƽ��185�����56%��  �������ǰ�������ϵ������Աȣ������Ʊط�ӳ�ھ���ʵ���ϡ�����ʡͨ��������Ȩ������ֱ��Ϊ��ҵ����Լ30��Ԫ֮�⣬�������������ļ��٣��г����������ӣ�������2016���ϰ��꣬���������г����塢ע���ʱ��ֱ�����28.6%��53.1%��ͨ�����贴�´�ҵ԰����������˫������ҵ3840�ҡ�ֱ�����ʱ��һЩ�˲�������ʶ���˼�����Ȩ���ܹ��ͷŵľ޴�������ڣ��Ű�֮ǰ���ɵ�Ŀ�⻻Ϊ�ᶨ��֧�֡�  ����������Ȩ��������Ȼ����ֻ�ں���ʡһ�ط������á��������������ߴ�����������йز����˽⵽����һ�����ݣ�������������������Ѵ�2013���394����ٵ�2016���274�ͬʱ�������ϰ��꣬�����������ҵ��48234�ң�ͬ������46%����Ͷ�ʶ�3620��Ԫ��ͬ������77%�����ݶԱȸ�������һ����������ʵ���Ǿ������������������������������г������Ĵ󱬷����Ǿ���ǰ�������޹�����ͨ����һϵ�еĸĸ����ʵ���˸��г���λ��Ϊ��ҵ�ɰ���Ⱥ�����ģ��������г���������ᴴ������Ŀ�ģ��ɴ˴������²�ҵ����ҵ̬����ģʽ�����չ�������й������¶��ܵļӿ�������  ��������ڹ�����ˣ�ͨ��������Ȩ����������Ȼ������ҵ����������������ĳ�Ƽ����޹�˾�Ľ���ά��ҵ��չ�������ݱ���������ά��34�ܷɻ�������������ȥ��ȫ���ƽ�����˹��ڷɻ�ά����ҵ��ʵ����ǿ��������������֮�⣬�ɻ�ά�޽������̵ļ򻯺�ͨ�سɱ��Ľ���������ҵ�����չ����Ҫԭ�򣬶���ͬ���������������м�����Ȩ�Ľ����������ǰ�ɻ�����ά�ް�����������Ҫ���������������ķ�ʱ�䳤�����Ѿ����࣬��������ҵ�Ĺ���Ч�ʣ����������ҵ�Ĺ���ɱ���������������Ȩ���������̼��ˣ���ҵ���콻�ϵ������ϣ��ڶ����������������ʱ��̡����ѵͣ�������Ǹ����ҵ������Ը��������������ҵ��ӪԽ�����  ����������ʵ���Ѿ�֤����������Ȩ�ķ���û�д�������Ȩ�Ĳ��ӻ������ڱ����Ƚ���ǰ�������ø���Щ��",20,false));
        Date b = new Date();
        System.out.println(b.getTime()-a.getTime());
        System.out.println("\n");

        List<Term> termList = NLPTokenizer.segment("�����ƽ�������Ȩ�ͷž�������������Ϣ�Ƽ����޹�˾");
        System.out.println("NLP�ִʣ�");
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
	 * ��������ʴʿ����
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
	 * HanLP��׼�ִ�
	 * @param content �ı�����
	 * @param res �Ƿ��������ʿ����
	 * @param tagPart �Ƿ��ע����
	 * @param patten �ִ�ģʽĬ��Ϊ1		1--��׼�ִʣ�2--�����ִʣ�3--ȫ�ִʣ�4--���ٷִʣ�5--CRF�ִ�
	 * @return
	 */
	public static List getWords(String content, boolean res, boolean tagPart, int patten){
		List<Term> segment = new ArrayList<Term>();
		if(patten==1){
			segment = HanLP.segment(content);
		}else if(patten==2){
			segment = IndexTokenizer.segment(content);
		}else if(patten==3){
			// DoubleArrayTrie�ִ�
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
	 * ����ʶ��
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
	 * HanLPȫ�ִ�
	 * @param content �ı�����
	 * @param res �Ƿ��������ʿ����
	 * @param tagPart �Ƿ��ע����
	 * @return
	 */
	public static List getAllWords(String content, boolean res, boolean tagPart){
		// DoubleArrayTrie�ִ�
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
     * �ؼ�����ȡ
     * @param content ��������
     * @param wordCount �ؼ�������
     * @param res �Ƿ��������ʿ����
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
     * �ؼ�����ȡ�����ض�Ӧ��Ȩ��
     * @param content �ı�����
     * @param wordCount ����
     * @param res �Ƿ���������
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
     * �Զ���ȡժҪ
     * @param document �ĵ�����
     * @param summaryCount  ժҪ����
     * @return
     */
    public static String getSummary(String document, int summaryCount) {
//        String document = "������Ȩ��Ҫ���Ѿ��������һ��ʱ���ˣ�Ŀǰ���ڴӸ����Ƕȡ����������ȫ����ʡ���������Ȳ��ƽ�����Ч��������ǰ�����ϰ���ڸ���ġ�����֤����Խ��Խ���ˣ�Ҳ������ý�����ž۽����ȵ��ˣ����š�Ȩ���嵥���������嵥���Ĺ��������嵥֮����Ȩ��������ʶ���ڳ�ΪԽ��Խ���쵼�ɲ���Ϊ���Ծ��������죬��̫��������ҲԽ��Խ�١�  ����������ˮůѼ��֪�����ƶ�������Ȩ���������Լ��ط����������ǶԼ�����Ȩ������Ѹ�٣������Եģ�ȴ��ȫ����ǧ�������ҵ�͸��ˡ���ҵ�͹�������ڹ��������������ĸ��ܾ��ǣ����ڰ��������ǰ��������ˣ��ɱ������ˣ�Ч�ʷ��������ˡ�����һ�����ߵĸ߶���������Ϊ������ȨЧ�����ԣ����û��������������õ�����Ч���ͷţ����ֳ�һƬ�����������������ٵľ��档  ��������6����Ѯ������ʡĳ���ز��������޹�˾����ס�����쵽�˽������̿���������������飬��ʱ15�������ա����øù�˾�칫�ҹ�����Ա��С��𾪡�����Ҫ����ǰ������10������ţ�һ�Ҽҵݲ��ϣ�û�кü����±�������������ӹ�ȥ�ĺü����µ����ڵİ���£��ڷ��Ʊ�á��������ҵ��г����У������ζ���Ȼ�����ζ����������ζ����ҵ��Ӫ����ɱ��Ľ��ͣ���תЧ�ʵ���ߣ��г�������������ǿ��  ��������ǰ��仯��ԭ����ڣ������ڵ���Χ���ż�����ȨΪ���ĵ�������2015�꣬����ʡ��̨���ƽ�������Ȩ���Źܽ�ϡ�ת������ְ�ܹ���������ͳ���ƶ�����������Ͷ��������ְҵ�ʸ��շѹ��������ƶȡ��̿������������ĸ�����ڼ�Ȩ���ڣ�ʡ�����������������2000���2543������������304������������������ȫ��ȡ��������88%���м�ƽ��130�����52%���ؼ�ƽ��185�����56%��  �������ǰ�������ϵ������Աȣ������Ʊط�ӳ�ھ���ʵ���ϡ�����ʡͨ��������Ȩ������ֱ��Ϊ��ҵ����Լ30��Ԫ֮�⣬�������������ļ��٣��г����������ӣ�������2016���ϰ��꣬���������г����塢ע���ʱ��ֱ�����28.6%��53.1%��ͨ�����贴�´�ҵ԰����������˫������ҵ3840�ҡ�ֱ�����ʱ��һЩ�˲�������ʶ���˼�����Ȩ���ܹ��ͷŵľ޴�������ڣ��Ű�֮ǰ���ɵ�Ŀ�⻻Ϊ�ᶨ��֧�֡�  ����������Ȩ��������Ȼ����ֻ�ں���ʡһ�ط������á��������������ߴ�����������йز����˽⵽����һ�����ݣ�������������������Ѵ�2013���394����ٵ�2016���274�ͬʱ�������ϰ��꣬�����������ҵ��48234�ң�ͬ������46%����Ͷ�ʶ�3620��Ԫ��ͬ������77%�����ݶԱȸ�������һ����������ʵ���Ǿ������������������������������г������Ĵ󱬷����Ǿ���ǰ�������޹�����ͨ����һϵ�еĸĸ����ʵ���˸��г���λ��Ϊ��ҵ�ɰ���Ⱥ�����ģ��������г���������ᴴ������Ŀ�ģ��ɴ˴������²�ҵ����ҵ̬����ģʽ�����չ�������й������¶��ܵļӿ�������  ��������ڹ�����ˣ�ͨ��������Ȩ����������Ȼ������ҵ����������������ĳ�Ƽ����޹�˾�Ľ���ά��ҵ��չ�������ݱ���������ά��34�ܷɻ�������������ȥ��ȫ���ƽ�����˹��ڷɻ�ά����ҵ��ʵ����ǿ��������������֮�⣬�ɻ�ά�޽������̵ļ򻯺�ͨ�سɱ��Ľ���������ҵ�����չ����Ҫԭ�򣬶���ͬ���������������м�����Ȩ�Ľ����������ǰ�ɻ�����ά�ް�����������Ҫ���������������ķ�ʱ�䳤�����Ѿ����࣬��������ҵ�Ĺ���Ч�ʣ����������ҵ�Ĺ���ɱ���������������Ȩ���������̼��ˣ���ҵ���콻�ϵ������ϣ��ڶ����������������ʱ��̡����ѵͣ�������Ǹ����ҵ������Ը��������������ҵ��ӪԽ�����  ����������ʵ���Ѿ�֤����������Ȩ�ķ���û�д�������Ȩ�Ĳ��ӻ������ڱ����Ƚ���ǰ�������ø���Щ��";
        List<String> sentenceList = HanLP.extractSummary(document, summaryCount);
        
        String summary="";
        if(sentenceList!=null && sentenceList.size()>0){
        	for(String s:sentenceList){
        		summary+=s+"��";
        	}
        }
        return summary;
    }

}
