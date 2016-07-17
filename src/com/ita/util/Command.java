package com.ita.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

	private String command = null;
	private boolean flag = false;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public static Command getCommand(String str1, String str2) {
		Command c = new Command();
		if ("L-PD".equals(str1) && str2 != null) {
			String[] s = str2.split(":");
			if (s.length == 2 && "did".equals(s[0].toLowerCase())) {
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(s[1]);
				if (isNum.matches()) {
					c.setCommand("L-PD" + " " + "dId" + ":" + s[1]);
					c.setFlag(true);
				}
			}
		} else if ("A-D".equals(str1) && str2 != null) {// A-D
														// dName:Test2,city:ZhuHa
			String[] s = str2.split(",");
			String[] ans = new String[2];
			if (s.length == 2) {
				for (int i = 0; i < s.length; i++) {
					String[] subS = s[i].split(":");
					if (subS.length == 2) {
						if ("dname".equals(subS[0].toLowerCase()))
							ans[0] = "dName" + ":" + subS[1];
						else if ("city".equals(subS[0].toLowerCase()))
							ans[1] = "city:" + subS[1];
						else
							break;
					} else {
						break;
					}
					if (i == 1)
						c.setFlag(true);
				}
			}
			if (c.getFlag()) {
				c.setCommand("A-D " + ans[0] + "," + ans[1]);
			}
		} else if ("A-P".equals(str1) && str2 != null) {// A-P
														// pName:john,tel:15625856958,dId:10,birthday:1988-11-02,salary:10000
			String[] s = str2.split(",");
			String[] ans = new String[5];
			if (s.length == 5) {
				for (int i = 0; i < s.length; i++) {
					String[] subS = s[i].split(":");
					if (subS.length == 2) {
						if ("pname".equals(subS[0].toLowerCase()))
							ans[0] = "pName" + ":" + subS[1];
						else if ("tel".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("^1[3|4|5|7|8]\\d{9}$");
							Matcher isTel = pattern.matcher(subS[1]);
							if (isTel.matches()) {
								ans[1] = "tel" + ":" + subS[1];
							} else {
								break;
							}
						} else if ("did".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("[0-9]*");
							Matcher isNum = pattern.matcher(subS[1]);
							if (isNum.matches()) {
								ans[2] = "dId" + ":" + subS[1];
							} else {
								break;
							}
						} else if ("birthday".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("[0-9]*");
							String[] sss = subS[1].split("-");
							if (sss.length == 3) {
								int times = 0;
								if (sss[0].length() == 4) {
									Matcher isNum = pattern.matcher(sss[0]);
									if (isNum.matches()) {
										times++;
									}
								}
								if (sss[1].length() == 2) {
									Matcher isNum = pattern.matcher(sss[1]);
									if (isNum.matches()) {
										times++;
									}
								}
								if (sss[2].length() == 2) {
									Matcher isNum = pattern.matcher(sss[2]);
									if (isNum.matches()) {
										times++;
									}
								}
								if (times == 3) {
									ans[3] = "birthday" + ":" + subS[1];
								} else {
									break;
								}
							} else {
								break;
							}
						} else if ("salary".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("[0-9]*");
							Matcher isNum = pattern.matcher(subS[1]);
							if (isNum.matches()) {
								ans[4] = "salary" + ":" + subS[1];
							} else {
								break;
							}
						} else
							break;
					} else {
						break;
					}
					if (i == 4)
						c.setFlag(true);
				}
			}
			if(c.getFlag()==true)
				c.setCommand("A-P " + ans[0] + "," + ans[1] + "," + ans[2] + "," + ans[3] + "," + ans[4]);
		} else if ("U-D".equals(str1) && str2 != null) { // U-D
															// dName:Test2,city:ZhuHai,dId:15
			String[] s = str2.split(",");
			String[] ans = new String[3];
			if (s.length == 3) {
				for (int i = 0; i < s.length; i++) {
					String[] subS = s[i].split(":");
					if (subS.length == 2) {
						if ("dname".equals(subS[0].toLowerCase()))
							ans[0] = "dName" + ":" + subS[1];
						else if ("city".equals(subS[0].toLowerCase()))
							ans[1] = "city:" + subS[1];
						else if ("did".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("[0-9]*");
							Matcher isNum = pattern.matcher(subS[1]);
							if (isNum.matches()) {
								ans[2] = "dId:" + subS[1];
							} else {
								break;
							}
						} else
							break;
					} else {
						break;
					}
					if (i == 2)
						c.setFlag(true);
				}
			}
			if (c.getFlag()) {
				c.setCommand("U-D " + ans[0] + "," + ans[1] + "," + ans[2]);
			}
		} else if ("U-P".equals(str1) && str2 != null) {//U-P pName:john,tel:15625856958,dId:10,birthday:1988-11-02,salary:10000,pId:206
			String[] s = str2.split(",");
			String[] ans = new String[6];
			if (s.length == 6) {
				for (int i = 0; i < s.length; i++) {
					String[] subS = s[i].split(":");
					if (subS.length == 2) {
						if ("pname".equals(subS[0].toLowerCase()))
							ans[0] = "pName" + ":" + subS[1];
						else if("pid".equals(subS[0].toLowerCase())){
							Pattern pattern = Pattern.compile("[0-9]*");
							Matcher isNum = pattern.matcher(subS[1]);
							if (isNum.matches()) {
								ans[5] = "pId" + ":" + subS[1];
							} else {
								break;
							}
						}
						else if ("tel".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("^1[3|4|5|7|8]\\d{9}$");
							Matcher isTel = pattern.matcher(subS[1]);
							if (isTel.matches()) {
								ans[1] = "tel" + ":" + subS[1];
							} else {
								break;
							}
						} else if ("did".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("[0-9]*");
							Matcher isNum = pattern.matcher(subS[1]);
							if (isNum.matches()) {
								ans[2] = "dId" + ":" + subS[1];
							} else {
								break;
							}
						} else if ("birthday".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("[0-9]*");
							String[] sss = subS[1].split("-");
							if (sss.length == 3) {
								int times = 0;
								if (sss[0].length() == 4) {
									Matcher isNum = pattern.matcher(sss[0]);
									if (isNum.matches()) {
										times++;
									}
								}
								if (sss[1].length() == 2) {
									Matcher isNum = pattern.matcher(sss[1]);
									if (isNum.matches()) {
										times++;
									}
								}
								if (sss[2].length() == 2) {
									Matcher isNum = pattern.matcher(sss[2]);
									if (isNum.matches()) {
										times++;
									}
								}
								if (times == 3) {
									ans[3] = "birthday" + ":" + subS[1];
								} else {
									break;
								}
							} else {
								break;
							}
						} else if ("salary".equals(subS[0].toLowerCase())) {
							Pattern pattern = Pattern.compile("[0-9]*");
							Matcher isNum = pattern.matcher(subS[1]);
							if (isNum.matches()) {
								ans[4] = "salary" + ":" + subS[1];
							} else {
								break;
							}
						}else
							break;
					} else {
						break;
					}
					if (i == 5)
						c.setFlag(true);
				}
			}
			if(c.getFlag()==true)
				c.setCommand("U-P " + ans[0] + "," + ans[1] + "," + ans[2] + "," + ans[3] + "," + ans[4]+","+ans[5]);
		} else if ("D-D".equals(str1) && str2 != null) {
			String[] s = str2.split(":");
			if (s.length == 2 && "did".equals(s[0].toLowerCase())) {
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(s[1]);
				if (isNum.matches()) {
					c.setCommand("D-D" + " " + "dId" + ":" + s[1]);
					c.setFlag(true);
				}
			}
		} else if ("D-P".equals(str1) && str2 != null) {
			String[] s = str2.split(":");
			if (s.length == 2 && "pid".equals(s[0].toLowerCase())) {
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(s[1]);
				if (isNum.matches()) {
					c.setCommand("D-P" + " " + "pId" + ":" + s[1]);
					c.setFlag(true);
				}
			}
		}
		return c;
	}

}
