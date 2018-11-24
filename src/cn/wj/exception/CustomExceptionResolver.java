package cn.wj.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * <p>Title: CustomExceptionResolver</p>
 * <p>Description: 鑷畾涔夊紓甯稿鐞嗗櫒</p>
 * <p>Company: www.itcast.com</p> 
 * @author	浼犳櫤.鐕曢潚
 * @date	2015-3-22涓嬪崍2:57:42
 * @version 1.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver  {

	//鍓嶇鎺у埗鍣―ispatcherServlet鍦ㄨ繘琛孒andlerMapping銆佽皟鐢℉andlerAdapter鎵цHandler杩囩▼涓紝濡傛灉閬囧埌寮傚父灏变細鎵ц姝ゆ柟娉�
	//handler鏈�粓瑕佹墽琛岀殑Handler锛屽畠鐨勭湡瀹炶韩浠芥槸HandlerMethod
	//Exception ex灏辨槸鎺ユ敹鍒板紓甯镐俊鎭�
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//杈撳嚭寮傚父
		ex.printStackTrace();
		
		//缁熶竴寮傚父澶勭悊浠ｇ爜
		//閽堝绯荤粺鑷畾涔夌殑CustomException寮傚父锛屽氨鍙互鐩存帴浠庡紓甯哥被涓幏鍙栧紓甯镐俊鎭紝灏嗗紓甯稿鐞嗗湪閿欒椤甸潰灞曠ず
		//寮傚父淇℃伅
		String message = null;
		CustomException customException = null;
		//濡傛灉ex鏄郴缁�鑷畾涔夌殑寮傚父锛岀洿鎺ュ彇鍑哄紓甯镐俊鎭�
		if(ex instanceof CustomException){
			customException = (CustomException)ex;
		}else{
			//閽堝闈濩ustomException寮傚父锛屽杩欑被閲嶆柊鏋勯�鎴愪竴涓狢ustomException锛屽紓甯镐俊鎭负鈥滄湭鐭ラ敊璇�
			customException = new CustomException("鏈煡閿欒");
		}
		
		//閿欒 淇℃伅
		message = customException.getMessage();
		
		request.setAttribute("message", message);

		
		try {
			//杞悜鍒伴敊璇�椤甸潰
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView();
	}

}
