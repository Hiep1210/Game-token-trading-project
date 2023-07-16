
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import tickRate.MarketItemsJobs;
import tickRate.AuctionJobs;
import tickRate.TradeItemJob;

@WebListener
public class BackgroundJobManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new MarketItemsJobs(), 0, 15, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new AuctionJobs(), 0, 15, TimeUnit.SECONDS);
       scheduler.scheduleAtFixedRate(new TradeItemJob(), 0, 15, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

}
