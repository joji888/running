package cn.edu.jsu.zjj.running.upload;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadFile {

    private static String lPath="/home/running/upload/";
    private static String wPath="E:\\File\\practical\\running\\upload";

    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();//获取当前运行系统环境
    /**
     * 多文件上传就是调用了下面的文件上传方法
     * @param uploadFileS
     * @return
     */
    public List<String> uploadFileS(MultipartFile[] uploadFileS, String[] urlS){
        List<String> fileUrlS=new ArrayList<String>();
        int i=0;
        try {
            //无修改图片时不执行
            if (urlS != null) {
                for (;i<urlS.length;i++){//先遍历图片地址数组
                    String fileUrl = uploadFile(uploadFileS[i], urlS[i]);//有图片地址，能匹配地址删除原文件
                    fileUrlS.add(fileUrl);//将新图片地址到路径数组中
                }
            }
            for (;i<uploadFileS.length;i++){//图片地址数组遍历完后继续遍历file数组
                String fileUrl = uploadFile(uploadFileS[i], null);//没有图片地址，直接上传文件
                fileUrlS.add(fileUrl);//将上传的图片地址添加到路径数组中
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrlS;
    }

    /**
     * 上传文件业务层
     * @param uploadFIle 需要上传的文件
     * @param url 原文件地址 (可用于覆盖原文件)
     * @return  返回最新的文件资源路径
     * @throws IOException
     */
    public String uploadFile(MultipartFile uploadFIle, String url) throws IOException {
        if (uploadFIle==null){
            return url;//用户没选中文件直接返回原文件路径
        }

        //文件名和后缀名处理
        String fileName=UUID.randomUUID().toString().replace("-","");//生成UUID,将生成的UUID的-踢掉
        String originalFilename = uploadFIle.getOriginalFilename();//拿源文件的文件名
        originalFilename=originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());//拿用户上传文件的后缀名
        fileName=fileName+originalFilename;//组合成新的文件名


        File file=createFile(fileName,url);//文件处理

        uploadFIle.transferTo(file);//执行上传
//        String imgUrl="http://39.107.14.94:19104/"+fileName;//组合并返回图片地址，第一段是图片服务器的访问地址(本机测试图片库)
        String imgUrl="file/"+fileName;//组合并返回图片地址，第一段是图片服务器的访问地址(本机测试图片库)

        LoggerFactory.getLogger(getClass()).info("上传图片的地址："+imgUrl);//日志输出

        return imgUrl;
    }





    /**
     * 文件上传辅助类，判断是否需要创文件，覆盖原文件，以及系统路径
     * @param fileName  新文件名
     * @param url  以前的文件路径
     * @return 放回处理好的文件路径
     */
    private File createFile(String fileName,String url) throws IOException {
        String path="";//路径初始化
        if (OS_NAME.contains("windows")){
            //运行环境为 windows
            LoggerFactory.getLogger(getClass()).info("windows");
            path=wPath;//本机文件上传测试文件夹
        }else {
            //运行环境为 linux
            LoggerFactory.getLogger(getClass()).info("linux");
            path=lPath;//服务器文件上传测试文件夹
        }

        //文件夹判断
        File file=new File(path);
        if (!file.exists()){
            file.mkdirs();//创建该文件夹
            LoggerFactory.getLogger(getClass()).info("文件夹不存在创建该文件夹，路径："+file.getCanonicalPath());
        }else {
            //文件存在
            if (!file.isDirectory()){
                //不是文件夹
                file.mkdirs();//创建该文件夹
                LoggerFactory.getLogger(getClass()).info("文件夹不存在创建该文件夹，路径："+file.getClass().getResource("/").getPath());
            }
        }

        deleteFile(url);//判断是否需要删除文件

        file=new File(path,fileName);//将上传的文件重命名
        return file;//返回最后的文件对象
    }

    /**
     * 删除文件
     * @return  返处理提示
     */
    public String deleteFile(String url){
        if (url==null){
            LoggerFactory.getLogger(getClass()).info("没有原图路径，不做删除处理");
            return "没有原图路径，不做删除处理";
        }
        url=url.replace(" ","");//去空格
        if (url.equalsIgnoreCase("null")||url.equals("")||url.equals("undefined")){
            LoggerFactory.getLogger(getClass()).info("没有原图路径，不做删除处理");
            return "没有原图路径，不做删除处理";
        }
        String path="";
        if (OS_NAME.contains("windows")){
            //运行环境为 windows
            LoggerFactory.getLogger(getClass()).info("windows");
//            path="D:/zs/File/";//中设数字媒体文件位置
            path=wPath;//windows文件路径
        }else {
            //运行环境为 linux
            LoggerFactory.getLogger(getClass()).info("linux");
            path=lPath;//linux文件路径
        }

        String fileName="";
        if (url.contains("/")){
            fileName=url.substring(url.lastIndexOf("/"), url.length()).replace("/","");//将源文件资源路径中的文件名拿到
        }

        File file=new File(path,fileName);//初始化要被删除的文件对象
        if (file.exists()){
            file.delete();//这个文件存在,删除这个文
            LoggerFactory.getLogger(getClass()).info("删除了文件:"+fileName);
            return "删除成功";
        }

        LoggerFactory.getLogger(getClass()).info(path+"\\"+fileName+"文件不存在，请到该文件位置检查文件");
        return "删除失败";

    }
}
