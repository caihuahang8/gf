package com.learn.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.learn.entity.RecordEntity;
import com.learn.entity.SysUserEntity;
import com.learn.service.RecordService;
import com.learn.service.SysUserService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.ZuoyeEntity;
import com.learn.service.ZuoyeService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 作业信息
 */
@RestController
@RequestMapping("zuoye")
public class ZuoyeController extends AbstractController {
    @Autowired
    private ZuoyeService zuoyeService;
    @Autowired
    RecordService recordService;
    @Autowired
    SysUserService sysUserService;

    private final  String path = "D:\\tomcat8\\tomcat-8.5.29_x64\\apache-tomcat-8.5.29\\webapps\\gf_war\\cdn\\cdn";
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {


        //查询列表数据
        Query query = new Query(params);

        List<ZuoyeEntity> zuoyeList = zuoyeService.queryList(query);
        int total = zuoyeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(zuoyeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ZuoyeEntity> zuoyeList = zuoyeService.queryList(query);
        return R.ok().put("list", zuoyeList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ZuoyeEntity zuoye = zuoyeService.queryObject(id);

        return R.ok().put("zuoye", zuoye);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoyeEntity zuoye) {
        zuoyeService.save(zuoye);
        Map<String, Object> params = new HashMap<>();
        params.put("type", "3");
        for (SysUserEntity user : this.sysUserService.queryList(params)) {
            RecordEntity entity = new RecordEntity();
            entity.setZuoye(zuoye.getId());
            entity.setUser(user.getUserId());
            this.recordService.save(entity);
        }


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ZuoyeEntity zuoye) {
        zuoyeService.update(zuoye);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        zuoyeService.deleteBatch(ids);

        return R.ok();
    }

    @RequestMapping("/v1/download/{id}")
    public R download(@PathVariable("id") Long id) throws IOException {
//        ZuoyeEntity zuoyeEntity = zuoyeService.queryObject(id);
//        String url = zuoyeEntity.getUrl();
//        String path = this.path + url.replace("/","\\");
//        String filename=url.substring(url.lastIndexOf("/")+1);
//        System.out.println(filename);
//        //如果下载的是中文文件名，则会乱码，需要使用url编码
//        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(filename,"UTF-8"));
//        InputStream in=null;
//        OutputStream out=null;
//        try {
//            in=new FileInputStream(path);
//            int len=0;
//            byte buffer[]=new byte[1024];
//            out=response.getOutputStream();
//            while((len=in.read(buffer))!=-1){
//                out.write(buffer,0,len);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(in!=null){
//                in.close();
//            }
//        }
//        return R.ok();
        return null;
    }

    public byte[] download(String path) {
        try{
            File file = new File(path);
            InputStream input = new FileInputStream(file);
            byte[] bytes = IOUtils.toByteArray(input);
            input.close();
            return bytes;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @RequestMapping("/download/{id}")
   public R download(@PathVariable("id") Long id,HttpServletResponse response) {
        ZuoyeEntity zuoyeEntity = zuoyeService.queryObject(id);
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

    @RequestMapping("")
    public String download(String url,HttpServletResponse response) {
        download(url);
        return null;
    }
}
