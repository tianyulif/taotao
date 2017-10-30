package com.taotao.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	@ResponseBody
	public List categoryList(@RequestParam(value="id", defaultValue="0") Long parentId) throws Exception {
		List<EasyUITreeNode> catList = new ArrayList();
		//查询数据库
		List<TbItemCat> list = itemCatService.getItemCatList(parentId);
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			//如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			catList.add(node);
		}
		return catList;
	}
	
	public static void main(String args[]) throws Exception, IOException{
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("10.68.5.170");
		ftpClient.login("ftpuser", "AAAaaa222");
		int reply = ftpClient.getReplyCode();
		System.out.println("reply="+reply);
		FileInputStream inputStream = new FileInputStream(new File("F:/123.jpg"));
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//ftpClient.enterLocalPassiveMode();
		ftpClient.storeFile("1234.jpg", inputStream);
		inputStream.close();
		ftpClient.logout();
	}


}
