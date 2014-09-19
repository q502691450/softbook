姓名：${userName?substring(0,2)}
=======================================
if的用法：
<#if userSex==0>
女
<#elseif userSex==1>
男
<#else>
其它
</#if>
=========================================
switch的用法：
<#switch userSex>
    <#case 0>
    女
   <#break >
    <#case 1>
   男
    <#break >
    <#default>
   其它
</#switch>
=========================================
list的用法：
<#list stringList as obj>
总个数：${stringList?size}-->下标：${obj_index}-->值：${obj}<#if obj_has_next>,</#if>
 <#if obj=='e'><#break ></#if>
</#list>
=========================================
include的用法：
<#include "*/test_include.ftl" encoding="UTF-8">
=========================================
import的用法：
<#import "footer.ftl" as foot>
<@foot.copyright address=address email= email phone=phone />
=========================================
noparse的用法：
<#noparse>
<#list stringList as obj>
    下标：${obj_index}-->值：${obj}<#if obj_has_next>,</#if>
    <#if obj=='e'><#break ></#if>
</#list>
</#noparse>
=========================================
compress的用法：
<#compress>
1 2 3 4 6 7

${userName}

去除多余的空白
</#compress>
=========================================
function的用法：
<#function avg x y>
    <#return  (x+y)/2 >
</#function>
${avg(10,30)}
=========================================



