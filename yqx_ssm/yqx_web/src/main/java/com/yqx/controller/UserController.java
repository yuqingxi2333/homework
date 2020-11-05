package com.yqx.controller;

import com.yqx.pojo.Role;
import com.yqx.pojo.UserInfo;
import com.yqx.service.RoleService;
import com.yqx.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Day04 注入角色表
     * 用户可以有多个角色
     * @Auto 于清晰
     */
    @Autowired
    private RoleService roleService;


    /**
     * Day03  添加用户信息
     * @param userInfo
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/save" )
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:/user/findAll.do";
    }

    /**
     *  Day03 查询所有用户信息
     * @return
     * @Auto yuqingxi
     */
    @RequestMapping( "/findAll" )
    public ModelAndView findAll(){
        List<UserInfo> userInfoList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "userList",userInfoList );
        modelAndView.setViewName( "user-list" );
        return modelAndView;
    }

    /**
     * Day04 用户页面 点击详情查询用户信息
     * 需要根据用户的id去查询所有的信息
     * @param id
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/findById" )
    public ModelAndView findById(@RequestParam( name = "id",required = true) String id){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo user = userService.findById( id );
        modelAndView.addObject( "user",user );
        modelAndView.setViewName( "user-show" );
        return modelAndView;
    }

    /**
     * Day04 根据传入的id 查找到对应的用户 及可以添加的角色
     * 一个用户可以有多个角色 多对多
     * @param id
     * @return
     */
    @RequestMapping( "/findUserByIdAndAllRole" )
    public ModelAndView findUserByIdAndAllRole( String id ){
        // 根据id查找到单个用户
        UserInfo user = userService.findById(id);
        // 根据用户的id  查找到该用户对应的所有角色
        List<Role> roleList = roleService.findOtherRole( id );
        ModelAndView modelAndView = new ModelAndView();
        // 将获取到的数据 发送给前台页面
        modelAndView.addObject( "user",user );
        modelAndView.addObject( "roleList",roleList );
        modelAndView.setViewName( "user-role-add" );
        return modelAndView;
    }

    /**
     * Day04 根据userId获取用户
     * 然后根据ids获取所有的角色信息
     * @param userId
     * @param ids
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/addRoleToUser" )
    public String addRoleToUser( String userId,String[] ids ){
        userService.addRoleToUser( userId,ids );
//        return "redirect:/user/findAll.do";
        return "redirect:findAll.do";
    }














    // ------------------------------------------------------------

    /**
     * 扩展 从Excel表中的数据中获取
     * 然后上传到数据库中
     * @param upload
     * @return
     */
  /*  @RequestMapping( "/importExcel" )
    public String importExcel(@PathVariable @RequestParam(name = "upload") MultipartFile file) throws IOException {
        // 解析excel
        Workbook wb = new XSSFWorkbook( file.getInputStream() );
        // 获取sheet 第一个
        Sheet sheet = wb.getSheetAt( 0 );

        // 获取sheet中的每一行和每一个单元格中的内容
        // getLastRowNum 获取最后一行的索引
        // getLastCellNum 获取最后一个单元格的索引
        List<UserInfo> list = new ArrayList<>();
        // 外循环循环行
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            // 获取每一行
            Row row = sheet.getRow(rowNum);
            // 获取到每一个单元格中的内容 封装到Object数组中
            // 数组长度为一行中单元格的最大数
            Object[] values = new Object[ row.getLastCellNum() ];
            // 内循环循环单元格
            for ( int cellNum = 0;cellNum < row.getLastCellNum(); cellNum++ ){

                // 根据索引获取每一个单元格
                Cell cell = row.getCell(cellNum);
                // 拼接到数组中 调用方法
                Object value = getValue( cell );
                // 放到数组中
                values[cellNum] = value;
            }
            // 把添加完的User对象 放入List集合中
            UserInfo userInfo = new UserInfo( values );
            list.add( userInfo );
        }
        // 遍历List集合 保存User对象到数据库中
        for (UserInfo userInfo : list) {
            userService.save( userInfo );
        }
        return "redirect:findAll.do";
    }
*/
    //导入excel文件
    @RequestMapping("/importExcel")
    public String importExcel(@RequestParam(name = "upload") MultipartFile file) throws Exception {
        //1.解析excel
        Workbook  wb  = new XSSFWorkbook(file.getInputStream());
        //获取sheet
        Sheet sheet = wb.getSheetAt(0);
        //3.获取sheet中的每一行和每一个单元格内容
        // getLastRowNum  最后一行的索引
        // getLastCellNum() 最后一行单元格的索引
        List<UserInfo>  list = new ArrayList<>();
        for (int rowNum = 1; rowNum <=sheet.getLastRowNum();rowNum++){
            //获取每一行   Row
            Row row = sheet.getRow(rowNum);
            //获取到每个单元格里的内容,封装到Object数组中.
            Object[]   values = new Object[row.getLastCellNum()];
            for (int cellNum = 0; cellNum<row.getLastCellNum();cellNum++){
                //根据索引获取每一个单元格
                Cell cell = row.getCell(cellNum);
                //拼接   需要定义方法
                Object value  =  getCell(cell);
                values[cellNum] = value;
            }
            UserInfo userInfo =  new UserInfo(values);
            list.add(userInfo);
            //保存到数据库中
        }
        //遍历list结合批量保存数据到数据库中
        for (UserInfo userInfo : list) {
            userService.save(userInfo);
        }

        return "redirect:findAll.do";
    }
    /**
     * 定义一个方法 该方法用于获取到读取Excel表格中的单元格的值
     * 因为单元格的值可能是任意类型 所以返回值使用Object接收
     * @param cell
     * @return
     */
    private  static Object getCell( Cell cell ){
        // 创建一个空对象
        Object value = null;
        // 使用Switch进行判断
        // 获取cell的属性
        switch ( cell.getCellType() ){
            // 字符串类型
            case STRING:
                value = cell.getStringCellValue();
                break;
            // 布尔类型
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            // 数字类型 -- 包含日期和所有数字类型
            case NUMERIC:
                // 进行判断 如果是日期类型的话
                if (DateUtil.isCellDateFormatted(cell) ){
                    value = cell.getDateCellValue();
                }else{
                    value = cell.getNumericCellValue();
                }
                break;
            // 公式类型
            case FORMULA:
                value = cell.getCellFormula();
                break;
            default:
                break;
        }
        // 返回value
        return value;

    }

    /**
     * 导出Excel文件
     * @param response
     */
    @RequestMapping( "/exportExcel" )
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 查询数据库 获取所有的UserInfo对象
        List<UserInfo> list = userService.findAll();
        // 创建工作簿
        Workbook wb = new XSSFWorkbook();
        // 定义仪表头
        String[] titles = {"编号","用户名","密码","邮箱","电话","状态"};
        // 构建sheet
        Sheet sheet = wb.createSheet( "yuqingxi导出" );
        // 创建行对象  创建第一行对象
        Row row = sheet.createRow( 0 );
        // 定义一个方法 该方法用于从左到右写入数据
        AtomicInteger headerAi = new AtomicInteger();
        // 循环遍历表头信息 写入到表格中
        for (String title : titles) {
            Cell cell = row.createCell(headerAi.getAndIncrement());
            // 把每一个元素 写入到第一行的表头中
            cell.setCellValue( title );
        }

        // 遍历数据库中查询的数据
        // 创建第二行
        AtomicInteger dataAi = new AtomicInteger(1);
        // 创建一个空单元格
        Cell cell;
        // 遍历数据库中查询到的数据
        for (UserInfo userInfo : list) {
            // 创建一行
            Row dataRow = sheet.createRow(dataAi.getAndIncrement());
            // 创建单元格
            cell = dataRow.createCell(0);
            // 往单元格中添加数据
            // 设置id
            cell.setCellValue( userInfo.getId() );

            // 设置username
            cell = dataRow.createCell(1);//创建单元格
            //往单元格中添加数据
            cell.setCellValue(userInfo.getUsername());

            // 设置password
            cell = dataRow.createCell(2);//创建单元格
            //往单元格中添加数据
            cell.setCellValue(userInfo.getPassword());

            // 设置email
            cell = dataRow.createCell(3);//创建单元格
            //往单元格中添加数据
            cell.setCellValue(userInfo.getEmail());

            // 设置手机号
            cell = dataRow.createCell(4);//创建单元格
            //往单元格中添加数据
            cell.setCellValue(userInfo.getPhoneNum());

            // 设置用户状态
            cell = dataRow.createCell(5);//创建单元格
            //往单元格中添加数据
            if ( userInfo.getStatus() == 0 ){
                cell.setCellValue( "关闭" );
            }else{
                cell.setCellValue( "开启" );
            }
        }
        // 最后固定写法
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(new Date().getTime()+"用户列表.xlsx", "UTF-8"));
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/octet-stream");
        wb.write(response.getOutputStream());
        wb.close();
    }






}
