use amf;
#订单日统计存储过程
drop procedure if exists sumOrderTurnover_day ;
delimiter //
#startTime 开始时间 endTime 结束时间
create procedure sumOrderTurnover_day(in startTime bigint,in endTime bigint,in dmId bigint,in dmId2 bigint)
begin
declare alipaySum decimal;#支付宝支付总数
declare weChatSum decimal;#微信支付总数
declare unionpaySum decimal;#银联支付总数
declare applyWithDrawalTotal decimal;#总申请提现金额
declare withDrawalTotal decimal;#提现金额
declare time bigint;
declare t_error int default 0;
declare continue handler for sqlexception set t_error=1;
set time=(select unix_timestamp(now())*1000);

start transaction;
select @alipaySum:=sum(case when o.payType=1 then o.paymentPrice end ),  @weChatSum:=sum(case when o.payType=2 then o.paymentPrice end ) , @unionpaySum:=sum(case when o.payType=4 then o.paymentPrice end )  from `order` o where o.crtime >= startTime and o.crtime <= endTime and o.payStatus=0; 
select @applyWithDrawalTotal:=sum(w.money) from withdrawals_apply w where w.handleStatus=1;
select @withDrawalTotal:=sum(w.money) from withdrawals_apply w where w.handleStatus=1;
#insert into test(`test`,`id`) values(time,dmId);
insert  into day_operation(`dmId`,`alipay`,`wechat`,`unionPay`,`turnover`,`withdrawals`,`playMoney`,`statisticalTime`,`crtime`) values(dmId,@alipaySum,@weChatSum,@unionpaySum,(@alipaySum+@weChatSum+@unionpaySum), @applyWithDrawalTotal,@withDrawalTotal,time,time);
insert into day_stat(`dmId`,`turnover`,`presentValue`,`statisticalTime`,`crtime`) values(dmId2,(@alipaySum+@weChatSum+@unionpaySum),@withDrawalTotal,time,time );
if t_error =1 
 then 
rollback;
 else 
commit;
 end if;
end;


drop procedure if exists sumOrderTurnover_week;

#统计一周数据 调用的时候请注意调用时间必须为没周一调用 weekTime 周时间
create procedure sumOrderTurnover_week(in dmId bigint,in weekTime bigint)
begin 
declare withDrawalTotal decimal;#提现金额
declare total decimal default 0;#总营业额
declare time bigint;
declare t_error int DEFAULT 0;
declare continue handler for sqlexception set t_error=1;
set time=(select unix_timestamp(now())*1000);
start transaction;
select @total:=sum(t.turnover),@withDrawalTotal:=sum(t.presentValue) from day_stat t where date_sub(DATE_FORMAT(FROM_UNIXTIME(weekTime,'%Y-%m-%d'),'%Y-%m-%d'), interval 7 day) <= date(from_unixtime(crtime/1000,"%Y-%m-%d"));
insert into week_stat(`dmId`,`turnover`,`presentValue`,`statisticalTime`,`crtime`) values(dmId,@total,@withDrawalTotal,time,time );
if t_error =1 
  then 
 rollback;
  else 
 commit;
 end if;
end;




drop procedure if exists sumOrderTurnover_month;

#统计一月数据 调用的时候请注意调用时间必须为没月1号 monTime 月时间
create procedure sumOrderTurnover_month(in dmId bigint,in monTime bigint)
begin 
declare withDrawalTotal decimal;#提现金额
declare total decimal default 0;#总营业额
declare time bigint;
declare t_error int DEFAULT 0;
declare continue handler for sqlexception set t_error=1;
set time=(select unix_timestamp(now())*1000);
start transaction;
select @total:=sum(turnover), @withDrawalTotal:=sum(presentValue)  from  `week_stat`  where date_sub(DATE_FORMAT(FROM_UNIXTIME(monTime,'%Y-%m-%d'),'%Y-%m-%d'), interval 1 month) <= date(from_unixtime(crtime/1000,"%Y-%m-%d"));
insert into month_stat(`dmId`,`turnover`,`presentValue`,`statisticalTime`,`crtime`) values(dmId,@total,@withDrawalTotal,time,time );
if t_error =1 
 then 
rollback;
 else 
commit;
 end if;
end;
//
#test
delimiter //
drop procedure if exists test;
create procedure test(out c varchar(20))
begin
select '1231231' into c;
select c;
end
//
#3show procedure status;
//
#call sumOrderTurnover_week(123123132)
