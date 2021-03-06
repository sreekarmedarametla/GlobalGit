package chainofresponsibility;


import gash.router.server.ServerState;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pipe.common.Common.Failure;
import pipe.work.Work.WorkMessage;
import routing.Pipe;


public class WriteResponseHandler extends Handler {

    protected static Logger logger = LoggerFactory.getLogger("LeaderIs");

    public WriteResponseHandler(ServerState state) {
        super(state);
    }

    public void processWorkMessage(WorkMessage msg, Channel channel) {
    	if(msg.getResponse().hasWriteResponse())
		{
			System.out.println("got the log response from follower");
			state.getManager().getCurrentState().responseToChuckSent(msg);
		}else {
        	System.out.println("no write response going to readrequest handler");
            next.processWorkMessage(msg, channel);
        }
    }

    @Override
    public void processCommandMessage(Pipe.CommandMessage message, Channel channel) {

    }
    /*
    @Override
    public void processGlobalMessage(Global.GlobalMessage message, Channel channel) {

    }

*/
}
