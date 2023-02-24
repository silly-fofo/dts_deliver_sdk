package com.aliyun.dts.deliver.core.bootstrap;

import com.aliyun.dts.deliver.DtsContext;
import com.aliyun.dts.deliver.base.Destination;
import com.aliyun.dts.deliver.base.Source;
import com.aliyun.dts.deliver.commons.config.GlobalSettings;
import com.aliyun.dts.deliver.commons.config.JobConfig;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.openapi.DtsOpenApi;
import com.aliyun.dts.deliver.commons.util.ReflectionUtils;
import com.aliyun.dts.deliver.core.runtime.DtsIntegrationRunner;
import com.aliyun.dts.deliver.core.runtime.standalone.StandaloneContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.common.metrics.JmxReporter;
import org.apache.kafka.common.metrics.MetricConfig;
import org.apache.kafka.common.metrics.Metrics;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DtsDeliver {

    /**
     * The settings of this process, you can get all config item from it.
     */
    protected Settings settings;

    private Metrics coreMetrics;
    private Metrics pluginMetrics;

    protected StandaloneContext standaloneContext;

    protected DtsContext dtsContext;

    private JobConfig jobConfig;

    private List<Source> sourceList;

    private Destination destination;

    public DtsDeliver(JobConfig jobConfig, List<Source> sourceList) {
        Destination destination = ReflectionUtils.newInstance("com.aliyun.dts.deliver.connector.desination.DStoreDestination");
        this.jobConfig = jobConfig;
        this.sourceList = sourceList;
        this.destination = destination;
    }

    public DtsDeliver(JobConfig jobConfig, List<Source> sourceList, Destination destination) {
        this.jobConfig = jobConfig;
        this.sourceList = sourceList;
        this.destination = destination;
    }

    public void startup() throws Exception {

        settings = jobConfig.getSettings();

        initializeContext();

        initMetrics();

        initializeStatusReporter();

        fetchSomeInfoByOpenApi();

        doCheck();

        doDeliver();
    }

    //todo(yanmen)
    private void initializeStatusReporter() {
    }

    //todo(yanmen)
    private void initMetrics() {
        MetricConfig metricConfig = new MetricConfig();
        metricConfig.timeWindow(5, TimeUnit.SECONDS);
        metricConfig.samples(2);

        JmxReporter jmxReporter = new JmxReporter("any");

        // initialize any-all core metrics
        coreMetrics = new Metrics(metricConfig);
        coreMetrics.addReporter(jmxReporter);
    }

    public static void redirectStdOutStdErr() throws FileNotFoundException {
        PrintStream printstream = new PrintStream(new FileOutputStream("./dts-deliver.out"));
        System.setOut(printstream);
        System.setErr(printstream);
    }

    private void fetchSomeInfoByOpenApi() {

        DtsOpenApi dtsOpenApi = new DtsOpenApi(
                GlobalSettings.ALIYUN_AK.getValue(settings),
                GlobalSettings.ALIYUN_SECRET.getValue(settings),
                GlobalSettings.DTS_INSTANCE_ID.getValue(settings)
        );

        Pair<String, String> userPassword = dtsOpenApi.getUserPassword();
        String dbList = dtsOpenApi.getDblist();
    }

    private void doCheck() {
    }

    private void doDeliver() {
        DtsIntegrationRunner integrationRunner = new DtsIntegrationRunner(settings, coreMetrics, destination, sourceList, standaloneContext.getDefaultContext());

        integrationRunner.start();
    }

    private void initializeContext() {
        standaloneContext = new StandaloneContext(settings);
    }
}
