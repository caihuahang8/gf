package com.learn.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.learn.entity.ZuoyeEntity;
import com.learn.utils.RRException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.ZiliaoEntity;
import com.learn.service.ZiliaoService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 资料中心
 */
@RestController
@RequestMapping("ziliao")
public class ZiliaoController extends AbstractController {
    @Autowired
    private ZiliaoService ziliaoService;
    private final  String path = "D:\\test\\cdn";
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {


        //查询列表数据
        Query query = new Query(params);

        List<ZiliaoEntity> ziliaoList = ziliaoService.queryList(query);
        int total = ziliaoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(ziliaoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ZiliaoEntity> ziliaoList = ziliaoService.queryList(query);
        return R.ok().put("list", ziliaoList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ZiliaoEntity ziliao = ziliaoService.queryObject(id);

        return R.ok().put("ziliao", ziliao);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZiliaoEntity ziliao) {
        ziliaoService.save(ziliao);
        if (ziliao.getUser() == null) {
            ziliao.setUser(super.getUserId());
        }


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ZiliaoEntity ziliao) {
        ziliaoService.update(ziliao);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        for (Long id : ids) {
            ZiliaoEntity entity = this.ziliaoService.queryObject(id);
            if (super.getUserId() > 1)
                if (!entity.getUser().equals(super.getUserId())) {
                    throw new RRException("请勿删除他人资料");
                }
        }

        return R.ok();
    }

    @RequestMapping("/download/{id}")
    public R download(@PathVariable("id") Long id, HttpServletResponse response) {
        ZiliaoEntity zuoyeEntity = ziliaoService.queryObject(id);
        String url  =  zuoyeEntity.getUrl();
        String URL = path +  "\\" + url.substring(url.lastIndexOf("/")+1);
        //通过文件名找出文件的所在目录
//      String URL = "D:\\test\\新建 XLS 工作表.xls";
        //得到要下载的文件
        File file = new File(URL);

        //如果文件不存在
        if(!file.exists()){
            //如果文件不存在，进行处理

            int  i=1/0;//系统会报错，除数不能为0.

            // return "modules/cms/front/themes/"+site.getTheme()+"/frontError";
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;
        //创建缓冲区
        byte[] b= new byte[1024];
        int len = 0;
        try {
            //读取要下载的文件，保存到文件输入流
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            response.setContentType("application/force-download");
            String filename = file.getName();
            //设置响应头，控制浏览器下载该文件
            response.addHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentLength( (int) file.length( ) );
            //循环将输入流中的内容读取到缓冲区当中
            while((len = inputStream.read(b)) != -1){
                //输出缓冲区的内容到浏览器，实现文件下载
                outputStream.write(b, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(inputStream != null){
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return R.ok("下载成功");
    }

}
