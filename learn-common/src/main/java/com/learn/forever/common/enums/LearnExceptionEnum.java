package com.learn.forever.common.enums;


/**
 * @author : Guava
 * @version 1.0
 * @projectName：bw-sales-plug
 * @className：SalesPlugExceptionEnum
 * @date 2020/1/29 12:34 下午
 * @E-mail:gongdexing@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2020 蓝鲸淘.
 * @return
 */
public enum LearnExceptionEnum implements IExceptionEnum {

    BW_SALESPLUG_990001(990001, "订单不存在"),
    BW_SALESPLUG_990002(990002, "没有待核销的订单"),
    BW_SALESPLUG_990003(940003, "前面排队有点长哦，等会再试叭"),
    BW_SALESPLUG_990004(940004, "参数错误"),
    BW_SALESPLUG_990005(940005, "非法的活动"),
    BW_SALESPLUG_990006(940006, "提现金额异常"),
    BW_SALESPLUG_990007(940007, "提现金额大于可提现金额"),
    BW_SALESPLUG_990008(940008, "用户账户状态不可用"),
    BW_SALESPLUG_990009(940009, "乐观锁获取失败"),
    BW_SALESPLUG_990010(940010, "未查询到银行卡信息"),
    BW_SALESPLUG_990011(940011, "非法操作"),
    BW_SALESPLUG_990012(940012, "该用户已经绑定了银行卡"),
    BW_SALESPLUG_990013(940013, "商品已下架"),
    BW_SALESPLUG_990014(940014, "商品已下架"),
    BW_SALESPLUG_990015(940015, "账单入账失败"),
    BW_SALESPLUG_990016(940016, "该活动已下架，不可操作"),
    BW_SALESPLUG_990017(940017, "包含违禁词"),
    BW_SALESPLUG_990018(940018, "请先绑定微信号"),
    BW_SALESPLUG_990019(940019, "生成支付单失败，请重试"),
    BW_SALESPLUG_990020(940020, "提货点不存在，请重新选择"),
    BW_SALESPLUG_990021(940021, "您选择的提货点，不存在拼团设置的提货点，请重新选择"),
    BW_SALESPLUG_990022(940022, "地址不存在"),
    BW_SALESPLUG_990023(940023, "不能操作不属于自己的数据"),
    BW_SALESPLUG_990024(940024, "数据不存在"),
    BW_SALESPLUG_990025(940025, "活动未开始"),
    BW_SALESPLUG_990026(940026, "当前订单不可再次申请退款"),
    BW_SALESPLUG_990027(940027, "操作失败"),
    BW_SALESPLUG_990028(940028, "退款金额不能为空，请重新输入"),
    BW_SALESPLUG_990029(940029, "不在退款中的订单不能操作退款"),
    BW_SALESPLUG_990030(940030, "账户余额不够，无法退款"),
    BW_SALESPLUG_990031(940031, "账户可提现余额不足"),
    BW_SALESPLUG_990032(940032, "请输入正确的银行卡号"),
    BW_SALESPLUG_990033(940033, "退款金额不能大于订单金额"),
    BW_SALESPLUG_990034(940034, "拒绝提现失败"),
    BW_SALESPLUG_990035(940035, "请输入提现金额"),
    BW_SALESPLUG_990036(940036, "请输入正确的提现金额"),
    BW_SALESPLUG_990037(940037, "每天提现最多5万"),
    BW_SALESPLUG_990038(940038, "今天提现次数已用完"),
    BW_SALESPLUG_990039(940039, "请选择收货地址"),
    BW_SALESPLUG_990040(940040, "订单号必传"),
    BW_SALESPLUG_990041(940041, "订单不存在"),
    BW_SALESPLUG_990042(940042, "订单已支付"),
    BW_SALESPLUG_990043(940043, "商品达到限购上限数"),
    BW_SALESPLUG_990044(940044, "已被退清，不可操作"),
    BW_SALESPLUG_990045(940045, "商品库存不足"),
    BW_SALESPLUG_990046(940046, "账户不可用"),
    BW_SALESPLUG_990047(940047, "请先申请团长再发布个人商品"),
    BW_SALESPLUG_990048(940048, "该活动已下架，不可操作"),
    BW_SALESPLUG_990049(940049, "站点地址已添加其他团不可删除"),
    BW_SALESPLUG_990050(940050, "错误或重复，请检查运单模版内容及格式并再次上传"),
    BW_SALESPLUG_990051(940051, "导入失败，请检查运单模版内容及格式并再次上传"),
    ;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;

    LearnExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
