package com.chinamobile.projectuser.util;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class Code { 
	private ByteArrayInputStream beteImage;// 图像
	private String str;// 验证码
	private static final int WIDTH = 100;
	private static final int HEIGHT = 40;

	private BufferedImage buffImage;
	private Random random = new Random();// 随机器
	
/*	public static void main(String[] arg) {
		Code vcu = Code.Instance(1);
		System.err.println(vcu.getVerificationCodeValue());
	}*/

	public static Code Instance(int type) {
		return new Code( type);
	}

	private Code(int type) {
		 buffImage = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
				
		// int randomNum = new Random().nextInt(3);
			//initNumVerificationCode(buffImage);
		 initLetterAndNumVerificationCode(buffImage);
	}

	/**
	 * 功能：设置第一种验证码的属性
	 */
	public void initNumVerificationCode(BufferedImage image) {

		Random random = new Random(); // 生成随机类
		Graphics g = initImage2(image, random);
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		this.setStr(sRand);/* 赋值验证码 */
		// 图象生效
		g.dispose();
		this.setImage(drawImage(image));
	}
	
	
	/**
	 * 功能：设置第二种验证码属性
	 */
	public void initLetterAndNumVerificationCode(BufferedImage image) {
		String[] captchars = { "4","5","6","7","A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","1","2","3",
				"W", "X", "Y", "Z","a","b","c","d","e","f","g","h","i","j","k","l","m","8","9","0","n","o","p","q","r","s","t","u","v","w","x","y","z" };
		String[] captchars2 = { "1","2","3","4","5","6","7","8","9","0"};
		String[] captchars3 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J","K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z"};
		String[] captchars4 = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z" };
		
		int num = 4;// 字符的多少
		
		int car = captchars.length - 1;
		Font font = getFont();// 获得写的时候的字体
		
		Graphics2D graphics = initImage2(image, random);
		
		List<String> tempRand = new ArrayList<String>();
		tempRand.add(captchars2[random.nextInt(9) + 1]);
		tempRand.add(captchars3[random.nextInt(25) + 1]);
		tempRand.add(captchars4[random.nextInt(25) + 1]);
		for (int i = 0; i < num-3; i++) {
			tempRand.add(captchars[random.nextInt(car) + 1]);
		}
		Collections.shuffle(tempRand);
		
		String sRand = "";
		for (String s : tempRand) {
			font = new Font("Fixedsys", Font.PLAIN, 25);
			
			sRand += s;
			graphics.setColor(new Color(20 + random.nextInt(10), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
		}
		
		Color c = getRandColor(200, 250);
		graphics.setColor(c);// 设置背景色
		
		graphics.setFont(font);
		graphics.setColor(new Color(20 + random.nextInt(10), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		graphics.drawString(sRand, 8, 30);// 将字写到图片上

		int w = image.getWidth();
		int h = image.getHeight();
		shear(graphics, w, h, c);// 使图片扭曲
		
		// 添加噪点
		float yawpRate = 0.012f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}
		
		this.setStr(sRand); //赋值验证码 
		graphics.dispose(); // 图象生效
		this.setImage(drawImage(image));
	}
	
	/**
	 * 产生随机字体
	 */
	private Font getFont() {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, 30);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, 30);
		font[2] = new Font("Fixedsys", Font.PLAIN, 30);
		font[3] = new Font("Wide Latin", Font.PLAIN, 30);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 30);
		return font[random.nextInt(5)];
	}
	
	public Graphics2D initImage2(BufferedImage image, Random random) {
		Graphics2D g = image.createGraphics(); // 获取图形上下文
		g.setColor(getRandColor(200, 250));// 设定背景色
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 15));// 设定字体
		// 从左上到右下加上多道干扰线
		g.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(image.getWidth() - 1);
			int y = random.nextInt(image.getHeight() - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl + 40, y + yl + 20);
		}
		// 从右上到左下加多道干扰线
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(image.getWidth() - 1);
			int y = random.nextInt(image.getHeight() - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl + 40, y - yl);
		}
		return g;
	}
	
	// 扭曲方法
	private void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}
	
	private void shearX(Graphics g, int w1, int h1, Color color) {
		int period = random.nextInt(2);
		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);
		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}
	
	// 添加噪点的方法
	private int getRandomIntColor() {
		int[] rgb = this.getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}
	
	private int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	private void shearY(Graphics g, int w1, int h1, Color color) {
		int period = random.nextInt(40) + 10; // 50;
		boolean borderGap = true;
		int frames = 10;
		int phase = 4;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}


	public Graphics initImage(BufferedImage image, Random random) {
		Graphics g = image.getGraphics(); // 获取图形上下文
		g.setColor(getRandColor(200, 250));// 设定背景色
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 14));// 设定字体
		g.setColor(getRandColor(160, 200)); // 随机产生165条干扰线，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 165; i++) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		return g;
	}

	public ByteArrayInputStream drawImage(BufferedImage image) {
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO
					.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
		}
		return input;
	}

	/*
	 * 功能：给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 功能：获取验证码的字符串值
	 * 
	 * @return
	 */
	public String getVerificationCodeValue() {
		return this.getStr();
	}

	/**
	 * 功能：取得验证码图片
	 * 
	 * @return
	 */
	public ByteArrayInputStream getImage() {
		return this.beteImage;
	}

	public void setImage(ByteArrayInputStream beteImage) {
		this.beteImage = beteImage;
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public BufferedImage getBuffImage() {
		return buffImage;
	}

	public void setBuffImage(BufferedImage buffImage) {
		this.buffImage = buffImage;
	}

	
}


