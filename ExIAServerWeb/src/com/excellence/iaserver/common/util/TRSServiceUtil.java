package com.excellence.iaserver.common.util;

import java.text.NumberFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.exke.resource.adapter.trs.service.WCMService;
import com.trs.client.Date;
import com.trs.client.TRSConnection;
import com.trs.client.TRSConstant;
import com.trs.client.TRSException;
import com.trs.client.TRSResultSet;

public class TRSServiceUtil {
	private static TRSServiceUtil INSTANCE;

	private static TRSServiceUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TRSServiceUtil();
		}
		return INSTANCE;
	}

	private TRSConnection conn = null;

	public boolean isConnected() {
		if (this.conn == null)
			return false;
		return !this.conn.isClosed();
	}

	public TRSConnection getConnection() throws TRSException {
		if (!this.isConnected()) {
			this.conn = new TRSConnection();
			if (!this.conn.connect(Constant.TRSSERVER_HOST,
					String.valueOf(Constant.TRSSERVER_PORT),
					Constant.TRSSERVER_ACCOUNT, Constant.TRSSERVER_PASSWORD,
					null)) {
				this.conn = null;
			}
		}
		return this.conn;
	}

	private static String executeSelect(String dbName, String whereCondition,
			String sortMethod, String stat, String defautCols,
			int searchOption, int hitPointType, boolean bContinue,
			Integer pageNo, Integer pageSize, Integer isReturnContent) {
		TRSConnection conn = null;
		TRSResultSet rs = null;
		try {
			conn = TRSServiceUtil.getInstance().getConnection();
			if (conn == null)
				throw new RuntimeException();
			
			rs = conn.executeSelect(dbName, whereCondition, sortMethod, stat,
					defautCols, searchOption, hitPointType, bContinue);

			WCMService wcmService = new WCMService();
			JSONObject result = new JSONObject();
			JSONArray arr = new JSONArray();
			NumberFormat num = NumberFormat.getPercentInstance();
			for (int i = (pageNo - 1) * pageSize; i < pageNo * pageSize
					&& i < rs.getRecordCount(); i++) {
				rs.moveTo(0, i);
				JSONObject object = new JSONObject();
				object.put("id", rs.getInt("metadataId"));
				object.put("channelId", rs.getInt("channelId"));
				String channelName = "";
				try {
					channelName = wcmService.getChannelName(rs.getInt("channelId"));
				} catch (Exception e) {
//					e.printStackTrace();
				}
				object.put("channelName", channelName);
				String resourceUrl = "";
				try {
					resourceUrl =  wcmService.generateSimpleUrl(rs.getInt("metadataId") + "");
				} catch (Exception e) {
//					e.printStackTrace();
				}
				object.put("resourceUrl",resourceUrl);
				object.put("title", rs.getString("title"));
				if(isReturnContent!=0) {
					object.put("content", rs.getString("content"));
					object.put("html_content", rs.getString("html_content"));
				} else {
					object.put("content", "");
					object.put("html_content", "");
				}
				object.put("content", rs.getString("content"));
				object.put("html_content", rs.getString("html_content"));
				object.put("abstract", rs.getString("abstract"));
				object.put("url", rs.getString("url"));
				object.put("keywords", rs.getString("keywords"));
				object.put("genre", rs.getString("genre"));
				object.put("form", rs.getString("form"));
				object.put("symbol", rs.getString("symbol"));
				object.put("provider", rs.getString("provider"));
				object.put("belong", rs.getString("belong"));
				object.put("category", rs.getString("category"));
				Date bbrqDate = rs.getDate("bbrq");
				if (bbrqDate != null) {
					object.put("bbrq",
							bbrqDate.getYear() + "-" + bbrqDate.getMonth()
									+ "-" + bbrqDate.getDate());
				} else {
					object.put("bbrq", "");
				}

				Double rate = rs.getRelevance();
				object.put("relevance", num.format(rate));
				arr.put(object);
			}
			result.put("page", pageNo);
			result.put("pageSize", pageSize);
			result.put("num", rs.getRecordCount());
			result.put("data", arr);
			return result.toString();
		} catch (TRSException e) {
			System.out.println("ErrorCode: " + e.getErrorCode());
			System.out.println("ErrorString: " + e.getErrorString());
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				rs.close();
			rs = null;

			if (conn != null)
				conn.close();
			conn = null;
		}
	}

	public static String fullSearch(String text, JSONArray infoSource,
			JSONArray genres, JSONArray forms, JSONArray symbols, int page,
			int pageSize) {

		StringBuffer sqlWhere = new StringBuffer();
		if (StringUtils.isNotBlank(text)) {
			sqlWhere.append(text + " ");
		}
		if (infoSource != null && infoSource.length() > 0) {
			if (infoSource.length() == 1) {
				sqlWhere.append(" and belong=" + infoSource.getString(0) + " ");
			} else {
				sqlWhere.append(" and belong=(");
				for (int i = 0; i < infoSource.length(); i++) {
					if (i == 0) {
						sqlWhere.append(infoSource.getString(i));
					} else {
						sqlWhere.append(" or " + infoSource.getString(i));
					}
				}
				sqlWhere.append(") ");
			}
		}
		if (genres != null && genres.length() > 0) {
			if (genres.length() == 1) {
				sqlWhere.append(" and belong=" + genres.getString(0) + " ");
			} else {
				sqlWhere.append(" and belong=(");
				for (int i = 0; i < genres.length(); i++) {
					if (i == 0) {
						sqlWhere.append(genres.getString(i));
					} else {
						sqlWhere.append(" or " + genres.getString(i));
					}
				}
				sqlWhere.append(") ");
			}
		}
		if (forms != null && forms.length() > 0) {
			if (forms.length() == 1) {
				sqlWhere.append(" and belong=" + forms.getString(0) + " ");
			} else {
				sqlWhere.append(" and belong=(");
				for (int i = 0; i < forms.length(); i++) {
					if (i == 0) {
						sqlWhere.append(forms.getString(i));
					} else {
						sqlWhere.append(" or " + forms.getString(i));
					}
				}
				sqlWhere.append(") ");
			}
		}
		if (symbols != null && symbols.length() > 0) {
			if (symbols.length() == 1) {
				sqlWhere.append(" and belong=" + symbols.getString(0) + " ");
			} else {
				sqlWhere.append(" and belong=(");
				for (int i = 0; i < symbols.length(); i++) {
					if (i == 0) {
						sqlWhere.append(symbols.getString(i));
					} else {
						sqlWhere.append(" or " + symbols.getString(i));
					}
				}
				sqlWhere.append(") ");
			}
		}
		return executeSelect(Constant.TRSSERVER_DATABASE, sqlWhere.toString(),
				"RELEVANCE", "", "title;content;html_content", 0,
				TRSConstant.TCE_ROWCOL, false, page, pageSize, 1);
	}

	/**
	 * 全文检索接口
	 * 
	 * @param conditions
	 *            Map<String, Object> conditions = new HashMap<String,Object>();
	 *            conditions.put("text", "查询词"); conditions.put("infoSources",
	 *            "wcmmetatableflfg,wcmmetatablespecialpolicy");
	 *            conditions.put("genres", "图书,新闻");
	 *            conditions.put("forms","公告,通知"); conditions.put("symbols",
	 *            "文字,声音"); conditions.put("title", "尊重不同文明共建和谐世界");
	 *            conditions.put("zdjg", "最高人民检察院 公安部");
	 *            conditions.put("bbwh","高检会[1991]31号"); conditions.put("sxx",
	 *            "紧急"); conditions.put("page", 1); conditions.put("pageSize",
	 *            10); conditions.put("isEqualPrev", false);
	 * @return
	 */
	public static String search(Map<String, Object> conditions) {
		int page = (Integer) conditions.get("page");
		int pageSize = (Integer) conditions.get("pageSize");
		int isReturnContent = (Integer) conditions.get("isReturnContent");
		StringBuffer sqlWhere = new StringBuffer();
		String text = (String) conditions.get("text");
		boolean hasPrev = false;
		if(StringUtils.isNotBlank(text) && text.indexOf(";")==-1 && text.indexOf(",")==-1) {
			sqlWhere.append(" "+text+" ");
			hasPrev = true;
		} else if(StringUtils.isNotBlank(text) && text.indexOf(",")!=-1 && text.indexOf(";")==-1) {//and
			String[] word = text.split(",");
			if (word.length==1) {
				sqlWhere.append(word[0]+" ");
			} else {
				sqlWhere.append("(");
				for (int i = 0; i < word.length; i++) {
					if (i==0) {
						sqlWhere.append(word[i]);
					} else {
						sqlWhere.append(" and "+word[i]);
					}
				}
				sqlWhere.append(") ");
			}
			hasPrev = true;
		} else if(StringUtils.isNotBlank(text) && text.indexOf(";")!=-1 && text.indexOf(",")==-1) {//or
			String[] word = text.split(";");
			if (word.length==1) {
				sqlWhere.append(word[0]+" ");
			} else {
				sqlWhere.append("(");
				for (int i = 0; i < word.length; i++) {
					if (i==0) {
						sqlWhere.append(word[i]);
					} else {
						sqlWhere.append(" or "+word[i]);
					}
				}
				sqlWhere.append(") ");
			}
			hasPrev = true;
		} else if(StringUtils.isNotBlank(text) && text.indexOf(";")!=-1 && text.indexOf(",")!=-1) {//or和and混合查询
			//TODO
			sqlWhere.append(" "+text+" ");
			hasPrev = true;
		}
		String metadataId = (String) conditions.get("metadataId");
		if (StringUtils.isNotBlank(metadataId)) {
			if (!hasPrev) {
				sqlWhere.append(" metadataId=" + metadataId + " ");
				hasPrev = true;

			} else {
				sqlWhere.append(" and metadataId=" + metadataId + " ");
			}
		}
		String title = (String) conditions.get("title");
		if (StringUtils.isNotBlank(title)) {
			if (!hasPrev) {
				sqlWhere.append(" title=" + title + " ");
				hasPrev = true;

			} else {
				sqlWhere.append(" and title=" + title + " ");
			}
		}
		String zdjg = (String) conditions.get("zdjg");
		if (StringUtils.isNotBlank(zdjg)) {
			if (!hasPrev) {
				sqlWhere.append(" zdjg=" + zdjg + " ");
				hasPrev = true;
			} else {
				sqlWhere.append(" and zdjg=" + zdjg + " ");
			}
		}
		String bbwh = (String) conditions.get("bbwh");
		if (StringUtils.isNotBlank(bbwh)) {
			if (!hasPrev) {
				sqlWhere.append(" bbwh=" + bbwh + " ");
				hasPrev = true;
			} else {
				sqlWhere.append(" and bbwh=" + bbwh + " ");
			}
		}
		String sxx = (String) conditions.get("sxx");
		if (StringUtils.isNotBlank(sxx)) {
			if (!hasPrev) {
				sqlWhere.append(" sxx=" + sxx + " ");
				hasPrev = true;
			} else {
				sqlWhere.append(" and sxx=" + sxx + " ");
			}
		}
		String infoSources = (String) conditions.get("infoSources");
		if (StringUtils.isNotEmpty(infoSources)) {
			String[] infoSource = infoSources.split(",");
			if (hasPrev) {
				sqlWhere.append(" and ");
				hasPrev = true;
			}
			if (infoSource.length == 1) {
				sqlWhere.append(" belong=" + infoSource[0] + " ");
			} else {
				sqlWhere.append(" belong=(");
				for (int i = 0; i < infoSource.length; i++) {
					if (i == 0) {
						sqlWhere.append(infoSource[i]);
					} else {
						sqlWhere.append(" or " + infoSource[i]);
					}
				}
				sqlWhere.append(") ");
			}
		}
		String genres = (String) conditions.get("genres");
		if (StringUtils.isNotEmpty(genres)) {
			String[] genre = genres.split(",");
			if (hasPrev) {
				sqlWhere.append(" and ");
				hasPrev = true;
			}
			if (genre.length == 1) {
				sqlWhere.append(" genre=" + genre[0] + " ");
			} else {
				sqlWhere.append(" genre=(");
				for (int i = 0; i < genre.length; i++) {
					if (i == 0) {
						sqlWhere.append(genre[i]);
					} else {
						sqlWhere.append(" or " + genre[i]);
					}
				}
				sqlWhere.append(") ");
			}
		}
		String forms = (String) conditions.get("forms");
		if (StringUtils.isNotEmpty(forms)) {
			String[] form = forms.split(",");
			if (hasPrev) {
				sqlWhere.append(" and ");
				hasPrev = true;
			}
			if (form.length == 1) {
				sqlWhere.append(" and form=" + form[0] + " ");
			} else {
				sqlWhere.append(" and form=(");
				for (int i = 0; i < form.length; i++) {
					if (i == 0) {
						sqlWhere.append(form[i]);
					} else {
						sqlWhere.append(" or " + form[i]);
					}
				}
				sqlWhere.append(") ");
			}
		}

		String symbols = (String) conditions.get("symbols");
		if (StringUtils.isNotEmpty(symbols)) {
			String[] symbol = symbols.split(",");
			if (hasPrev) {
				sqlWhere.append(" and ");
				hasPrev = true;
			}
			if (symbol.length == 1) {
				sqlWhere.append(" symbol=" + symbol[0] + " ");
			} else {
				sqlWhere.append(" symbol=(");
				for (int i = 0; i < symbol.length; i++) {
					if (i == 0) {
						sqlWhere.append(symbol[i]);
					} else {
						sqlWhere.append(" or " + symbol[i]);
					}
				}
				sqlWhere.append(") ");
			}
		}

		String category = (String) conditions.get("category");
		if (StringUtils.isNotEmpty(category)) {
			String[] categories = category.split(",");
			if (hasPrev) {
				sqlWhere.append(" and ");
				hasPrev = true;
			}

			if (categories.length == 1) {
				sqlWhere.append(" category=%" + categories[0] + "% ");
			} else {
				sqlWhere.append(" category=(");
				for (int i = 0; i < categories.length; i++) {
					if (i == 0) {
						sqlWhere.append("%" + categories[i].trim() + "%");
					} else {
						sqlWhere.append(" or %" + categories[i].trim() + "% ");
					}
				}
				sqlWhere.append(") ");
			}
		}

		return executeSelect(Constant.TRSSERVER_DATABASE, sqlWhere.toString(),
				"RELEVANCE", "", "", TRSConstant.TCM_MIXSORT
						| TRSConstant.TCE_OFFSET | TRSConstant.TCM_DOCOR
						| TRSConstant.TCM_SORTALWAYS, TRSConstant.TCM_DRAWTEXT
						| TRSConstant.TCM_LINETEXT | TRSConstant.TCM_HOTSPOTHIT
						| TRSConstant.TCM_SUMMARYHIT, false, page, pageSize, isReturnContent);

	}
	
}
