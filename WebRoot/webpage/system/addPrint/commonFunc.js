	//应用上下文
	var appCtx='/bbsp';
	//弹出窗口
	function newWindow(width, height, url) {
		window.open(url, "", "width=" + width + ",height=" + height + ",status=no,resizable=no,dependent=yes,alwaysRaised=yes,top=200,left=200,scrollbars=yes");
	}
	
	
	function newPrintWindow(width, height, url) {
		window.open(url, "", "width=" + width + ",height=" + height + ",status=no,resizable=yes,dependent=yes,alwaysRaised=yes,top=200,left=200,scrollbars=yes");
	}
	
	
	//客户信息选择窗口
	function customerSelectWindow() {
		var ret=window.showModalDialog('/bbsp/common/enterFrame.jhtml','','dialogWidth=612px;dialogHeight=412px;resizable=no;scroll=no;status=no;');
		return ret;
	}

	//选择客户
	function selectCustomer() 
	{
		newWindow(600,400,'common/selectCust.jhtml');
	}
	function  replace(inputstring)   
	{   
	  var   s   =   inputstring;
	  while(s.indexOf("&lt")!= -1 || s.indexOf("&gt;")!= -1 || s.indexOf("&quot;")!= -1 || s.indexOf("&amp;")!= -1 || s.indexOf("/r/n") != -1 ||s.indexOf("&#39;")!= -1 )
	  {
		s = s.replace("&lt;","<");   
		s = s.replace("&gt;",">");
		s = s.replace("&quot;","'");
		s = s.replace("&#39;","'");
		s = s.replace("&amp;","&");
		s = s.replace("/r/n","<br>");
	  }
	  return(s);   
	}
	function doPrint(url) {
//		newPrintWindow(800,600,url);
		doPrintForArgsHidden(url);
	}
	
	/**
	* window.open() url 参数以post方式提交 (原理 先打开一个空白页 再在此页面创建一个form 在提交)
	* 将所有的参数包装为form的属性
	* 
	*/
	function doPrintForArgsHidden(url) {
			var winset="temp";
			var tempForm=document.createElement("form");
			tempForm.id="tempForm1";
			tempForm.method="post";
			tempForm.action=url.split("?")[0];;
			tempForm.target=winset;			
			formateURL(tempForm,url);
			tempForm.attachEvent("onsubmit",function(){
				window.open("", "temp", "width=800,height=600,status=no,resizable=yes,dependent=yes,alwaysRaised=yes,top=200,left=200,scrollbars=yes");
			});
			document.body.appendChild(tempForm);
			tempForm.submit();
			document.body.removeChild(tempForm);
	}
	/**
	 * 格式化处理打印的URL
	 * 将打印的url包装为form对应的属性
	 * 
	 * @param url
	 */
	function formateURL(myForm,url){
		var urlStr = url.split("?")[1];
		var args=urlStr.split("&");
		for(i=0;i<args.length;i++){		
			var temp =args[i];
			if(temp!=null&&temp!=''){
				var flds =temp.split("=");
				setFormFieldProperty(myForm,flds[0],flds[1]);
			}
		}
	}
	function  setFormFieldProperty(myForm,myFiledName,myFileldValue){
		var typeInput=document.createElement("input");
		typeInput.type="hidden";
		typeInput.name=myFiledName;
		typeInput.value=myFileldValue;
		myForm.appendChild(typeInput);
	}
//excel import animation
