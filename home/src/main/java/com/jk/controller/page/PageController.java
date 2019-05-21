package com.jk.controller.page;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;
import com.jk.utils.ConstantConf;
import com.jk.utils.Morse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import static com.google.zxing.client.j2se.MatrixToImageConfig.BLACK;
import static com.google.zxing.client.j2se.MatrixToImageConfig.WHITE;

@Controller
@RequestMapping("member")
public class PageController {
    //跳转首页
    @RequestMapping("toHomePage")
    public String toHomePage(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "index";
    }
    //跳转物流专线
    @RequestMapping("toLogisticsLine")
    public String toLogisticsLine(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "logisticsLine";
    }
    //跳转在线发货
    @RequestMapping("toLogisticsLine")
    public String toOnlineDelivery(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "onlineDelivery";
    }
    //跳转国际海运
    @RequestMapping("toInternationalTransport")
    public String toInternationalTransport(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "internationalTransport";
    }
    @RequestMapping("toLogisticsCompany")
    public String toLogisticsCompany(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "logisticsCompany";
    }
    //跳转新闻
    @RequestMapping("toJournalism")
    public String toJournalism(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "journalism";
    }
    //跳转物流招标
    @RequestMapping("toCallForBids")
    public String toCallForBids(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "callForBids";
    }
    //跳转在线购买
    @RequestMapping("toPurchase")
    public String toPurchase(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "purchase";
    }
    //跳转个人中心
    @RequestMapping("toPersonalCenter")
    public String toPersonalCenter(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "personalCenter";
    }
    /**
     * @Author chh
     * @Description //TODO     到注册页面
     * @Date 17:27 2019/5/19
     * @Param
     * @return
     **/
    @RequestMapping("toRegs")
    public String toRegs(){
        return "regs";
    }
    /**
     * @Author chh
     * @Description //TODO  跳转到注册   表单页面
     * @Date 18:07 2019/5/19
     * @Param
     * @return
     **/

    @RequestMapping("regsFaHuo")
    public String regsFaHuo(Integer usertype, Model model){

        model.addAttribute("usertype",usertype);
        return "faHuo";

    }
    /**
     * @Author chh
     * @Description //TODO     到登陆页面    带着model
     * @Date 17:26 2019/5/19
     * @Param
     * @return            phoneNumber    password
     **/
    @RequestMapping("toLogin")
    public String comLogin(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        Morse morse = new Morse();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ConstantConf.cookieNamePaw)) {
                    String value = cookie.getValue();
                    if (value != null) {
                        String[] split = value.split(ConstantConf.splitC);
                        if(split.length>1){
                            model.addAttribute("phoneNumber", split[0]);
                            model.addAttribute("password", morse.decode(split[1]));
                        }else{
                            model.addAttribute("phoneNumber", split[0]);
                        }
                    }
                }
            }
        }
        return "login";
    }
    /**
     * @Author chh
     * @Description //TODO       注销
     * @Date 10:31 2019/5/17
     * @Param                 usertype    password   phoneNumber
     * @return                uuid   type   code  msg
     **/
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,String url){
        HttpSession session = request.getSession();
        session.removeAttribute(session.getId());
        if (url == "" || url == null) {
            url = "index";
        }
        return url;
    }
    /**
     * @Author chh
     * @Description //TODO       生成二维码
     * @Date 10:31 2019/5/17
     * @Param                 usertype    password   phoneNumber
     * @return                uuid   type   code  msg
     **/
    @RequestMapping("verificationCode")
    public void grtCode(HttpServletRequest request, HttpServletResponse response,String url) throws IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        if (url == "" || url == null) {
            url = "/member/toHomePage";
        }
        String requestUrl = request.getScheme() //当前链接使用的协议
                +"://" + ConstantConf.SERVER_NAME//服务器地址
                + ":" + request.getServerPort() //端口号
                + url;
        System.out.println(requestUrl);
        int width = 235;
        int height = 235;
        String content= requestUrl;
        String format = "png";
        String contents=new String(content.getBytes("UTF-8"),"ISO-8859-1");
        HashMap<EncodeHintType,Comparable> map=new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height,map);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.clearRect(0, 0, width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) == true ? BLACK : WHITE);
            }
        }
        response.reset();
        response.setContentType("image/png");
        ImageIO.write(image, format, response.getOutputStream());
    }
}
