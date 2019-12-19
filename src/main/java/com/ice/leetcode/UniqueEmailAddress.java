package com.ice.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。
 * <p>
 * 例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。
 * <p>
 * 除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。
 * <p>
 * 如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名称中没有点的同一地址。例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）
 * <p>
 * 如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，例如 m.y+name@email.com 将转发到 my@email.com。 （同样，此规则不适用于域名。）
 * <p>
 * 可以同时使用这两个规则。
 * <p>
 * 给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * 输出：2
 * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * 每封 emails[i] 都包含有且仅有一个 '@' 字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-email-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @ClassName EmailAddressFilter
 * @Description TODO
 * @Author liubin
 * @Date 2019/12/16 3:03 PM
 **/
public class UniqueEmailAddress {

    public static void main(String[] args) {
        int num = numUniqueEmails2(new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"});
        System.out.println("总共需要发送不同的邮件地址:" + num);
    }

    /**
     * 利用正则匹配方法
     *
     * @param emails
     * @return
     */
    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String[] split = email.split("@");
            String prefix = split[0].replaceAll("\\.", "");
            prefix = prefix.replaceAll("\\+.*", "");
            set.add(new StringBuilder(prefix).append("@").append(split[1]).toString());
        }
        return set.size();
    }

    /**
     * 利用状态机思路（将各个特殊字符看成各种状态，不同的状态做不同的处理）
     *
     * @param emails
     * @return
     */
    public static int numUniqueEmails2(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            StringBuilder sb = new StringBuilder();
            //2是指@前面的碰到+的状态
            int flag = 0;
            for (int i = 0; i < email.length(); i++) {
                switch (email.charAt(i)) {
                    case '.':
                        if (flag == 1) {
                            sb.append(email.charAt(i));
                        }
                        break;
                    case '+':
                        if (flag == 0) {
                            flag = 2;
                        }
                        break;
                    case '@':
                        flag = 1;
                    default:
                        if (flag != 2) {
                            sb.append(email.charAt(i));
                        }
                }
            }
            set.add(sb.toString());
        }
        return set.size();
    }

}
