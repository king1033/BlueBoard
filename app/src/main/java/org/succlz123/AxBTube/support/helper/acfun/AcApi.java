package org.succlz123.AxBTube.support.helper.acfun;

import org.succlz123.AxBTube.bean.acfun.AcReBanner;
import org.succlz123.AxBTube.bean.acfun.AcReHot;

import java.util.HashMap;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by fashi on 2015/7/19.
 */
public class AcApi {
	/**
	 * @return 都有而且不用改变的url参数
	 */
	public static HashMap getBaseMap() {
		HashMap map = new HashMap();
		map.put(AcString.APP_VERSION, AcString.APP_NUM);
		map.put(AcString.SYS_NAME, AcString.SYS_NAME_ANDROID);
		map.put(AcString.SYS_VERSION, AcString.SYS_VERSION_ANDROID);
		map.put(AcString.RESOLUTION, AcString.RESOLUTION_WIDTH_HEIGHT);
		return map;
	}

	/**
	 * http://api.acfun.tv/apiserver
	 * /recommend/list
	 * ?app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
	 *
	 * @return 首页横幅
	 */
	public static HashMap getAcReBannerUrl() {
		HashMap map = getBaseMap();
		return map;
	}

	public interface getAcReBanner {
		@GET(AcString.RECOMMEND_LIST)
		void onResult(@QueryMap() Map<String, String> options, retrofit.Callback<AcReBanner> cb);
	}

	/**
	 * http://api.acfun.tv/apiserver
	 * /recommend/page
	 * ?pageSize=10&pageNo=1
	 * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
	 *
	 * @return 热门焦点
	 */
	public static HashMap getAcReHotUrl() {
		HashMap<String, String> map = getBaseMap();
		map.put(AcString.PAGE_SIZE, AcString.PAGE_SIZE_NUM);
		map.put(AcString.PAGE_NO, AcString.PAGE_NO_NUM);

		return map;
	}

	public interface getAcReHot {
		@GET(AcString.RECOMMEND_PAGE)
		void onResult(@QueryMap() Map<String, String> options, retrofit.Callback<AcReHot> cb);
	}

//	/**
//	 * http://api.acfun.tv/apiserver/
//	 * content/channel
//	 * ?channelIds=106,107,108,109,67,120
//	 * &pageSize=20&pageNo=1
//	 * &orderBy=5
//	 * &range=604800000
//	 * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
//	 *
//	 * @return 动画区
//	 */
//	public static String getAcReAnimation() {
//
//	}


	/**
	 * http://api.acfun.tv/apiserver
	 * /content/channel
	 * ?channelIds=67
	 * &pageSize=20&pageNo=1
	 * &orderBy=7
	 * &range=604800000
	 * &app_version=118&sys_name=android&sys_version=5.1.1&market=m360&resolution=1080x1776
	 *
	 * @return 新番连载
	 */
	public static HashMap getAcAnimationBangumiUrl(String orderBy, String range) {
		HashMap map = getBaseMap();
		map.put(AcString.CHANNEL_IDS, AcString.BANGUMI);
		map.put(AcString.PAGE_SIZE, AcString.PAGE_NO_NUM);
		map.put(AcString.ORDER_BY, orderBy);
		map.put(AcString.RANGE, range);

		return map;
	}

	public interface getAcAnimationBangumi {
		@GET(AcString.CONTENT_CHANNEL)
		void onResult(@QueryMap() Map<String, String> options, retrofit.Callback<AcReHot> cb);
	}

}