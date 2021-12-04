import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ShellTest {

    public static void main(String[] args) throws JSchException {


        Session session = JschUtil.getSession("devServer", 22, "root", "123456");
//        session.connect(3000);
        Channel channel = session.openChannel("shell");
        channel.setInputStream(System.in);
        channel.setOutputStream(System.out);
        channel.connect(3000);

        while (true){}
//        ChannelExec channelExec = (ChannelExec) channel;
//        channelExec.setCommand("pwd");
    }

}
