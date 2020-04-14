package com.learn.forever.core.util;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 重试规则解析
 */
@Slf4j
public class RetryRule {

    /**
     * 重试规则，请严格桉照1s-5s-10s-1m这种格式来配置
     */
    @Getter
    private String rule;

    @Getter
    private List<RuleItem> rules = Lists.newArrayList();

    public RetryRule(String rule) {
        this.rule = rule;
        parseRules();
    }

    public long calcRetryTime(long baseTimestamp, int retryTimes) {
        if (retryTimes > rules.size()) {
            retryTimes = rules.size();
        }

        RuleItem ruleItem = rules.get(retryTimes - 1);
        return baseTimestamp + ruleItem.getPlusMills();
    }


    public long calcRetryTime(int retryTimes) {
        return calcRetryTime(System.currentTimeMillis(), retryTimes);
    }

    private void parseRules() {
        String[] tmpRules = StringUtils.split(rule, "-");

        for (String tmpRule : tmpRules) {
            String item = tmpRule.replace(" ", "").toLowerCase();
            long plusMills = this.calcPlusMills(item);

            RuleItem ruleItem = new RuleItem();
            ruleItem.setItem(item);
            ruleItem.setPlusMills(plusMills);
            rules.add(ruleItem);
        }
    }


    @Getter
    @Setter
    private class RuleItem extends PrintFriendliness {

        private String item;
        private long plusMills;
    }


    /**
     * 通过规则计算需要增加的毫秒数
     */
    private long calcPlusMills(String item) {

        if (item.contains("ms")) {
            return Integer.parseInt(item.replace("ms", ""));
        }

        if (item.contains("s")) {
            return Integer.parseInt(item.replace("s", "")) * 1000L;
        }

        if (item.contains("m")) {
            return Integer.parseInt(item.replace("m", "")) * 60L * 1000L;
        }

        if (item.contains("h")) {
            return Integer.parseInt(item.replace("h", "")) * 60 * 60L * 1000L;
        }

        if (item.contains("d")) {
            return Integer.parseInt(item.replace("d", "")) * 24L * 60 * 60 * 1000L;
        }

        throw new RuntimeException("retryrule config error, rule:" + rule);
    }
}









































