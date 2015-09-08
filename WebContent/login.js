/*var data = [
            {'name':{'first':'Leonard','last':'Marx'},'nickname':'Chico','born':'March 21, 1887','actor': true,'solo_endeavours':[{'title':'Papa Romani'}]},
            {'name':{'first':'Adolph','last':'Marx'},'nickname':'Harpo','born':'November 23, 1888','actor':true,'solo_endeavours':[{'title':'Too Many Kisses','rating':'favourite'},{'title':'Stage Door Canteen'}]},
            {'name':{'first':'Julius Henry','last':'Marx'},'nickname':'Groucho','born': 'October 2, 1890','actor':true,'solo_endeavours':[{'title':'Copacabana'},{'title':'Mr. Music','rating':'favourite'},{'title':'Double Dynamite'}]},
            {'name':{'first':'Milton','last':'Marx'},'nickname':'Gummo','born':'October 23, 1892'},
            {'name':{'first':'Herbert','last':'Marx'},'nickname':'Zeppo','born':'February 25, 1901','actor':true,'solo_endeavours':[{'title':'A Kiss in the Dark'}]}
        ];
Tempo.prepare('marx-brothers').render(data);*/

$(function(){
	
	$.fn.serializeObject = function(){  
        var o = {};  
        var a = this.serializeArray();  
        $.each(a, function(){  
            if (o[this.name]) {  
                if (!o[this.name].push) {  
                    o[this.name] = [o[this.name]];  
                }  
                o[this.name].push(this.value || '');  
            }  
            else {  
                o[this.name] = this.value || '';  
            }  
        });  
        return o;  
    }; 
    
    $("#formByJsonBut").click(function(){  
        var jsonuserinfo = $.toJSON($('#formByJson').serializeObject());
        log.debug(jsonuserinfo);  
        jQuery.ajax({  
            type: 'POST',  
            contentType: 'application/json',  
            url: 'app/demo/pub/loginByJson.do',  
            data: jsonuserinfo,  
            dataType: 'json',  
            success: function(result){
            	log.debug("登录成功！");
                log.debug("状态码:" + result.code + "    状态信息:" + result.msg + "   结果集：" + result.data.length);  
                log.debug("username:" + result.data.username + "   pwd:" + result.data.password);
        },  
            error: function(result){  
                log.error("error -->");
                for(p in result){
                	log.error("属性名：" + p + "  属性值：" + result.p);
                }
            }  
        });  
    }); 
});