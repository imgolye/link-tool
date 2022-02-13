package org.springlink.core.boot.ctrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springlink.core.boot.file.LinkFile;
import org.springlink.core.boot.file.LinkFileUtil;
import org.springlink.core.secure.LinkUser;
import org.springlink.core.secure.utils.SecureUtil;
import org.springlink.core.tool.api.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Link控制器封装类
 *
 * @author GL
 */
public class LinkController {

	/**
	 * ============================     REQUEST    =================================================
	 */

	@Autowired
	private HttpServletRequest request;

	/**
	 * 获取request
	 *
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * 获取当前用户
	 *
	 * @return LinkUser
	 */
	public LinkUser getUser() {
		return SecureUtil.getUser();
	}

	/** ============================     API_RESULT    =================================================  */

	/**
	 * 返回ApiResult
	 *
	 * @param data 数据
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public <T> R<T> data(T data) {
		return R.data(data);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public <T> R<T> data(T data, String msg) {
		return R.data(data, msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param data 数据
	 * @param msg  消息
	 * @param code 状态码
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public <T> R<T> data(T data, String msg, int code) {
		return R.data(code, data, msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param msg 消息
	 * @return R
	 */
	public R success(String msg) {
		return R.success(msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param msg 消息
	 * @return R
	 */
	public R fail(String msg) {
		return R.fail(msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param flag 是否成功
	 * @return R
	 */
	public R status(boolean flag) {
		return R.status(flag);
	}


	/**============================     FILE    =================================================  */

	/**
	 * 获取LinkFile封装类
	 *
	 * @param file 文件
	 * @return LinkFile
	 */
	public LinkFile getFile(MultipartFile file) {
		return LinkFileUtil.getFile(file);
	}

	/**
	 * 获取LinkFile封装类
	 *
	 * @param file 文件
	 * @param dir  目录
	 * @return LinkFile
	 */
	public LinkFile getFile(MultipartFile file, String dir) {
		return LinkFileUtil.getFile(file, dir);
	}

	/**
	 * 获取LinkFile封装类
	 *
	 * @param file        文件
	 * @param dir         目录
	 * @param path        路径
	 * @param virtualPath 虚拟路径
	 * @return LinkFile
	 */
	public LinkFile getFile(MultipartFile file, String dir, String path, String virtualPath) {
		return LinkFileUtil.getFile(file, dir, path, virtualPath);
	}

	/**
	 * 获取LinkFile封装类
	 *
	 * @param files 文件集合
	 * @return LinkFile
	 */
	public List<LinkFile> getFiles(List<MultipartFile> files) {
		return LinkFileUtil.getFiles(files);
	}

	/**
	 * 获取LinkFile封装类
	 *
	 * @param files 文件集合
	 * @param dir   目录
	 * @return LinkFile
	 */
	public List<LinkFile> getFiles(List<MultipartFile> files, String dir) {
		return LinkFileUtil.getFiles(files, dir);
	}

	/**
	 * 获取LinkFile封装类
	 *
	 * @param files       文件集合
	 * @param dir         目录
	 * @param path        目录
	 * @param virtualPath 虚拟路径
	 * @return LinkFile
	 */
	public List<LinkFile> getFiles(List<MultipartFile> files, String dir, String path, String virtualPath) {
		return LinkFileUtil.getFiles(files, dir, path, virtualPath);
	}


}
