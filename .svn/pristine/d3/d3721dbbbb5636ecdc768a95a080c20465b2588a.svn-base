package com.herongtech.console.core.common.json;

import com.herongtech.appconfig.SysConfigUtil;

public class Page {
	
    private int showCount; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage = 1;	//当前页
	private int currentResult = 1;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	
	
	private String pageCommand;
	private int directPage;
	private String selectOptionHtml;
	
	
	public Page(){
		try {
			this.showCount = Integer.parseInt(SysConfigUtil.getSysConfig().getValue("pageCount"));
		} catch (Exception e) {
			this.showCount = 50;
		}
	}
	
	public int getTotalPage() {
		
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getPageStr() {
		
		totalPage = getTotalPage();
		currentPage = getCurrentPage();
		
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("	<ul class=\"pagination\">\n");
			if(currentPage==1){
				sb.append("	<li><a class=\"btn btn-minier btn-white\">共<font color=red>"+totalResult+"</font>条</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\""+currentPage+"\" id=\"toGoPage\" min=\"1\" max=\""+totalPage+"\" onblur=\"checkPageInput(this)\" data-bind=\"value:replyNumber\" style=\"width:50px;height:31px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a class=\"btn btn-minier btn-success\" onclick=\"toTZ();\" >跳转</a></li>\n");
				sb.append("	<li><a class=\"btn btn-minier btn-white\">首页</a></li>\n");
				sb.append("	<li><a class=\"btn btn-minier btn-white\">上页</a></li>\n");
			}else{
				sb.append("	<li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\""+currentPage+"\" id=\"toGoPage\" min=\"1\" max=\""+totalPage+"\" onblur=\"checkPageInput(this)\" data-bind=\"value:replyNumber\" style=\"width:50px;height:31px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a class=\"btn btn-minier btn-success\" onclick=\"toTZ();\">跳转</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a class=\"btn btn-minier btn-white\" onclick=\"nextPage(1);\">首页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a class=\"btn btn-minier btn-white\" onclick=\"nextPage("+(currentPage-1)+");\">上页</a></li>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag=currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li><a class=\"btn btn-minier btn-white\" ><font color='#808080'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a class=\"btn btn-minier btn-white\" onclick=\"nextPage("+i+");\">"+i+"</a></li>\n");
			}
			if(currentPage == totalPage){
				sb.append("	<li><a class=\"btn btn-minier btn-white\">下页</a></li>\n");
				sb.append("	<li><a class=\"btn btn-minier btn-white\">尾页</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a class=\"btn btn-minier btn-white\" onclick=\"nextPage("+(currentPage+1)+")\">下页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a class=\"btn btn-minier btn-white\" onclick=\"nextPage("+totalPage+")\">尾页</a></li>\n");
			}
			sb.append("	<li><a class=\"btn btn-minier btn-white\">第"+currentPage+"页</a></li>\n");
			sb.append("	<li><a class=\"btn btn-minier btn-white\">共"+totalPage+"页</a></li>\n");
			
			
			sb.append("	<li><select title='显示条数' style=\"width:55px;height:31px;float:left;border-color:none;\" onchange=\"changeCount(this.value)\">\n");
			sb.append("	<option value='"+showCount+"'>"+showCount+"</option>\n");
			sb.append("	<option value='30'>30</option>\n");
			sb.append("	<option value='50'>50</option>\n");
			sb.append("	<option value='80'>80</option>\n");
			sb.append("	<option value='100'>100</option>\n");
			sb.append("	</select>\n");
			sb.append("	</li>\n");
			sb.append("</ul>\n");
			sb.append("	<input type=\"hidden\" name=\"page.totalResult\" value=\""+totalResult+"\"></input>");

            sb.append("<script type=\"text/javascript\">\n");
            
            //换页函数
            sb.append("function nextPage(page){");
            //sb.append(" top.jzts();");
            sb.append(" $(this).addClass(\"active\");");
            sb.append(" if(true && document.forms[0]){\n");
            sb.append("     var url = document.forms[0].getAttribute(\"action\");\n");
            sb.append("     if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
            sb.append("     else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
            sb.append("     url = url + page + \"&" +(entityOrField?"showCount":"page.showCount")+"="+showCount+"\";\n");
            sb.append("     url = url + \"&page.totalResult="+totalResult+"\";\n");

            sb.append("     document.forms[0].action = url;\n");
            sb.append("     document.forms[0].submit();\n");
            sb.append(" }else{\n");
            sb.append("     var url = document.location+'';\n");
            sb.append("     if(url.indexOf('?')>-1){\n");
            sb.append("         if(url.indexOf('currentPage')>-1){\n");
            sb.append("             var reg = /currentPage=\\d*/g;\n");
            sb.append("             url = url.replace(reg,'currentPage=');\n");
            sb.append("         }else{\n");
            sb.append("             url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
            sb.append("         }\n");
            sb.append("     }else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
            sb.append("     url = url + page + \"&" +(entityOrField?"showCount":"page.showCount")+"="+showCount+"\";\n");
            sb.append("     url = url + \"&page.totalResult="+totalResult+"\";\n");
            sb.append("     document.location = url;\n");
            sb.append(" }\n");
            sb.append("}\n");
            
            //调整每页显示条数
            sb.append("function changeCount(value){");
            //sb.append(" top.jzts();");
            sb.append(" if(true && document.forms[0]){\n");
            sb.append("     var url = document.forms[0].getAttribute(\"action\");\n");
            sb.append("     if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
            sb.append("     else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
            sb.append("     url = url + \"1&" +(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
            sb.append("     url = url + \"&page.totalResult="+totalResult+"\";\n");
            sb.append("     document.forms[0].action = url;\n");
            sb.append("     document.forms[0].submit();\n");
            sb.append(" }else{\n");
            sb.append("     var url = document.location+'';\n");
            sb.append("     if(url.indexOf('?')>-1){\n");
            sb.append("         if(url.indexOf('currentPage')>-1){\n");
            sb.append("             var reg = /currentPage=\\d*/g;\n");
            sb.append("             url = url.replace(reg,'currentPage=');\n");
            sb.append("         }else{\n");
            sb.append("             url += \"1&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
            sb.append("         }\n");
            sb.append("     }else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
            sb.append("     url = url + \"&" +(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
            sb.append("     url = url + \"&page.totalResult="+totalResult+"\";\n");
            sb.append("     document.location = url;\n");
            sb.append(" }\n");
            sb.append("}\n");
            
            //跳转函数 
            sb.append("function toTZ(){\n");
            sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;\n");
            sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}\n");
            sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}\n");
            sb.append("nextPage(toPaggeVlue);\n");
            sb.append("}\n");
            
            //跳转函数 
            sb.append("function checkPageInput(obj){\n");
            sb.append("var toPaggeVlue = obj.value;\n");
            sb.append("if(Number(toPaggeVlue) <=0 ){obj.value=1}\n");
            sb.append("}\n");
            sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	
	public int getShowCount() {
		return showCount;
	}
	
	public void setShowCount(int showCount) {
		
		this.showCount = showCount;
	}
	
	public int getCurrentResult() {
	
		return currentResult;
	}
	
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	
	public boolean isEntityOrField() {
		return entityOrField;
	}
	
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
	
	

    public void first() {
        currentPage = 1;
        currentResult = 1;
    }

    public void previous() {
        if (currentPage == 1) {
            return;
        }
        currentPage--;
        currentResult = (currentPage - 1) * showCount+1;
    }

    public void next() {
        if (currentPage < totalPage) {
            currentPage++;
        }
        currentResult = (currentPage - 1) * showCount+1;
    }

    public void last() {
        currentPage = totalPage;
        currentResult = (currentPage - 1) * showCount+1;
    }

    

    public int getDirectPage() {
        return directPage;
    }

    public void setDirectPage(int directPage) {
        this.directPage = directPage;
    }

    /**
     *直接指定跳转，判断与当前页的关系，然后执行相关跳转
     */
    public void refreshByDirectPage() {
        int trueNum = this.getDirectPage();
        if (trueNum < 1)
            trueNum = 1;
        else if (trueNum > this.getTotalPage())
            trueNum = this.getTotalPage();
        else
            trueNum = this.getDirectPage();
        
        boolean previous=trueNum<this.getCurrentPage();
        if(previous){//如果上一页,则向前翻
            int abs=this.getCurrentPage()-trueNum;
            for(int i=0;i<abs;i++){
                previous();
            }
        }else{
            int abs=trueNum-this.getCurrentPage();
            for(int i=0;i<abs;i++){
                next();
            }
        }
        setDirectPage(trueNum);
        
        
    }
    
    
    public String getPageCommand() {
        return pageCommand;
    }

    //接受页面传递过来的分页动作
    public void setPageCommand(String pageCommand){
        this.pageCommand=pageCommand;
    }
    
    public void activeCommand(){
        if("directPage".equalsIgnoreCase(getPageCommand())){//直接跳转
            this.refreshByDirectPage();
        }else if("first".equalsIgnoreCase(getPageCommand())){
            this.first();
        }else if("last".equalsIgnoreCase(getPageCommand())){
            this.last();
        }else if("previous".equalsIgnoreCase(getPageCommand())){
            this.previous();
        }else if("next".equalsIgnoreCase(getPageCommand())){
            this.next();
        }else{
        }
    }
    
    
    public String getSelectOptionHtml(){
        StringBuffer sb=new StringBuffer();
        for(int i=1;i<=this.getTotalPage();i++){
            String checked="";
            
            if(this.getCurrentPage()==i){
                checked="selected";
            }
            sb.append("<option value=\""+i+"\" "+checked+">"+i+"/"+this.getTotalPage()+"</option>\n");
            
        }
         selectOptionHtml=sb.toString();
        return selectOptionHtml;
    }
	
	
}
