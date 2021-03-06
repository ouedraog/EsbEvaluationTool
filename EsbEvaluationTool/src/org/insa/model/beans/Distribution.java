package org.insa.model.beans;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.simpleframework.xml.Attribute;

/**
 * Class Distribution
 *
 * @author Yahya Albaba, Deepali Jain, Marc Orfila, Juste Abel Ouedraogo, Vansh
 * Pahwa
 */
public class Distribution {

    /**
     * the minimum
     */
    @Attribute
    private double min;

    /**
     * The maximum
     */
    @Attribute
    private double max;

    /**
     * The mean
     */
    @Attribute
    private double average;

    /**
     * The standard deviation
     */
    @Attribute
    private double stdev;

    public Distribution() {
    }

    public Distribution(double min, double max, double average, double stdev) {
        this.min = min;
        this.max = max;
        this.average = average;
        this.stdev = stdev;
    }

    public Distribution(DescriptiveStatistics stats) {
        if (stats.getValues().length != 0) {
            this.min = stats.getMin();
            this.max = stats.getMax();
            this.average = stats.getMean();
            this.stdev = stats.getStandardDeviation();
        }
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getStdev() {
        return stdev;
    }

    public void setStdev(double stdev) {
        this.stdev = stdev;
    }

    /**
     * compute the distribution
     *
     * @param data the data
     */
    public void computeDist(double data[]) {
        min = StatUtils.min(data);
        max = StatUtils.max(data);
        average = StatUtils.mean(data);
        stdev = Math.sqrt(StatUtils.variance(data));
    }

    @Override
    public String toString() {
        return "min = " + min + ", max=" + max + ", average=" + average + ", stdev=" + stdev;
    }

    public double get(int type) {
        double res = 0;
        switch (type) {
            case 0:
                res = average;
                break;
            case 1:
                res = min;
                break;
            case 2:
                res = max;
                break;
            case 3:
                res = stdev;
                break;
        }
        return res;
    }

}
