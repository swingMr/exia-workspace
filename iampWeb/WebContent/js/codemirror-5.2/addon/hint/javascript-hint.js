/** 
 * 2017/02/27 yc ��дjavascript-hint.js 
 * @param {Object} mod 
 * @author LiuZeHang
 */  
(function(mod) {  
    if (typeof exports == "object" && typeof module == "object") // CommonJS  
        mod(require("../../lib/codemirror"), require("../../mode/javascript/javascript"));  
    else if (typeof define == "function" && define.amd) // AMD  
        define(["../../lib/codemirror", "../../mode/javascript/javascript"], mod);  
    else // Plain browser env  
        mod(CodeMirror);  
})(function(CodeMirror) {  
    "use strict";  
    var Pos = CodeMirror.Pos;  
    /** 
     * ��javascript.js���ȡkeyword���� 
     * @param {Object} editor 
     */  
    function getKeywords(editor) {  
        var mode = editor.doc.modeOption;  
        if (mode === "javascript") mode = "text/javascript";  
        return CodeMirror.resolveMode(mode).keywords;  
    };  
    /** 
     * �ж�Ԫ��item�Ƿ��������arr��   
     * @param {Object} arr 
     * @param {Object} item 
     */  
    function arrayContains(arr, item) { // �ж�Ԫ��item�Ƿ��������arr��    
        if (!Array.prototype.indexOf) {  
            var i = arr.length;  
            while (i--) {  
                if (arr[i] === item) {  
                    return true;  
                }  
            }  
            return false;  
        }  
        return arr.indexOf(item) != -1;  
    };  
      
    function hintJavascript(editor, keywords, tableKeywords, getToken, options) { // ����hint�ĺ��ĺ���������ΪvelocityHint(Ҳ���Բ����޸�)    
        // Find the token at the cursor����ȡ��ǰ���ָ�����ַ���    
        var cur = editor.getCursor(),  
            token = getToken(editor, cur),  
            tprop = token;  
        return {  
            list: getCompletions(token, keywords, tableKeywords, options),  
            from: Pos(cur.line, fetchStartPoint(token)), // �ַ���ƴ�ӵĳ�ʼλ�ã��������Ҫ    
            to: Pos(cur.line, token.end)  
        };  
    };  
    /** 
     * �ַ�ƴ��λ�� 
     * @param {Object} token 
     */  
    function fetchStartPoint(token) {  
        var index = token.string.lastIndexOf("\.");  
        if (index < 0) {  
            return token.start;  
        } else {  
            return token.start + index + 1;  
        }  
        //      return token.start;  
    };  
  
    function javascriptHint(editor, options) {  
        var keywords = wordToString(getKeywords(editor)) + CodeMirror.keywords;  
        return hintJavascript(editor, keywords, CodeMirror.tableKeywords, function(e, cur) {  
            return e.getTokenAt(cur);  
        }, options);  
    };  
    CodeMirror.registerHelper("hint", "javascript", javascriptHint);  
    /** 
     * �õ�ƥ��Ĺؼ������� 
     * @param {Object} token 
     * @param {Object} keywords 
     * @param {Object} tableKeywords 
     * @param {Object} options 
     */  
    function getCompletions(token, keywords, tableKeywords, options) {  
        var found = [],  
            start, pointCount, content = getWord(token.string, token.end); // foundΪƥ�������    
        if (content && content.length) {  
            start = token.string.charAt(0); //�ַ�������ĸ  
            content = content.trim().substring(0, content.lenght); //������ĸ��Ľ�ȡ  
            pointCount = (start == '\.') ? true : false; //�ж����һ���ַ��Ƿ���.  
        }  
        var result = null;  
        if (start && start.trim() != '') { // ������$��ͷ��������ʱ������${}
        	if(content !=")"){
                var regexp = new RegExp("\\b" + content + "\\S+\\b", "gi"); 	
        	}
            if (pointCount && tableKeywords) {  
                result = tableKeywords.split(" ");  
            } else {  
            	if(content!="_"){
            		result = tableKeywords.match(regexp);
            		if(result!=null&&result[0]!=""){
	            		for(var t=0; t<result.length; t++){
	            			result[t] = result[t]+")";
	            		}
            		}
            	}else{
            		result = keywords.split(" ");
            	}
            }  
            console.log('result = ' + result);  
        }  
        if (result && result.length) {  
            for (var i = 0; i < result.length; i++) {  
                if (!arrayContains(found, result[i]) && content.length <= result[i].length && pointCount) {  
                    if (result[i].charAt(result[i].length-1) == '.') { //������һλ��'.'  
                        found.push(result[i].substring(content.lastIndexOf("\.") + 1, result[i].length - 1));  
                    } else {  
                        found.push(result[i].substring(content.lastIndexOf("\.") + 1, result[i].length));  
                    }  
                } else {  
                    found.push(result[i]);  
                }  
            }  
        }  
        return found;  
    };  
    /** 
     * ��ȡ��ǰ�ַ��� 
     * @param {Object} str ��ǰ���ַ��� 
     * @param {Object} end ����λ�� 
     */  
    function getWord(str, end) {  
        return str.substring(str.lastIndexOf(' '), end);  
    };  
    /** 
     * ��wordlistƴ���ַ��� 
     * @param {Object} wordlist 
     */  
    function wordToString(wordlist) {  
        var str = '';  
        for (var word in wordlist) {  
            str += word + ' ';  
        }  
        return str;  
    };  
});  