import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ryder Roth, Jack Haehl
 * @version 1
 * @since 11/28/2020
 * This is the chart class, which
 * creates a chart given a repository
 */
public class Chart{

    /**
     * This is the dataContainer for the dataPoints
     * given by repository
     */
    ArrayList<DataPointContainer> dataContainer;

    /**
     * Takes in a repository and creates the dataContainer
     * that holds the datapoints to be plotted.
     * @param repository
     */
    public Chart(Repository repository)
    {
        dataContainer = new ArrayList<>();

        ArrayList<Attendance> attendanceList =  repository.getAttendances();
        for (Attendance attendance : attendanceList)
        {
            DataPointContainer dataContainerToAdd = new DataPointContainer(attendance);
            dataContainer.add(dataContainerToAdd);
        }
    }

    /**
     * Assembles the dataSet, then plots it on a new JFrame.
     */
    public void createAndDisplayChart()
    {
        XYSeriesCollection dataset = makeDataSet();

        JFreeChart attendancePlot = ChartFactory.createScatterPlot("Attendance Frequency",
                "Percentage of Time Attended", "Number of Students", dataset);

        ChartPanel chartPanel = new ChartPanel(attendancePlot);
        chartPanel.setVisible(true);

        JFrame chartFrame = new JFrame("Attendance Plot");
        chartFrame.setVisible(true);
        chartFrame.setSize(800, 600);
        chartFrame.setContentPane(chartPanel);
    }

    // Makes the dataset using the dataContainer the class stores
    private XYSeriesCollection makeDataSet()
    {
        XYSeriesCollection dataSet = new XYSeriesCollection();

        for (DataPointContainer dataPoints: dataContainer)
        {
            XYSeries xySeriesToAdd = createXYSeries(dataPoints);
            dataSet.addSeries(xySeriesToAdd);
        }

        return dataSet;
    }

    // Creates an XYSeries to put into the XYSeries collection
    // used to plot the scatterplot.
    private XYSeries createXYSeries(DataPointContainer dataContainer)
    {
        Date seriesDate = dataContainer.getDate();
        String dateAsString = dateToString(seriesDate);
        XYSeries xySeriesToReturn = new XYSeries(dateAsString);
        int xPoint, yPoint;

        for (int i = 0; i < dataContainer.NUM_OF_POINTS; i++)
        {
            xPoint = dataContainer.getXData().get(i);
            yPoint = dataContainer.getYData().get(i);
            xySeriesToReturn.add(xPoint, yPoint);
        }

        return xySeriesToReturn;
    }

    /**
     * Turns a date into a string that can be used to identify
     * a dataset on the scatterplot.
     * @param date
     * @return
     */
    private String dateToString(Date date)
    {
        String dateAsString = "";
        int month = date.getMonth();
        int day = date.getDate();
        int year = date.getYear() + 1900;

        switch (month)
        {
            case 0:
                dateAsString += "Jan ";
                break;
            case 1:
                dateAsString += "Feb ";
                break;
            case 2:
                dateAsString += "Mar ";
                break;
            case 3:
                dateAsString += "Apr ";
                break;
            case 4:
                dateAsString += "May ";
                break;
            case 5:
                dateAsString += "Jun ";
                break;
            case 6:
                dateAsString += "Jul ";
                break;
            case 7:
                dateAsString += "Aug ";
                break;
            case 8:
                dateAsString += "Sep ";
                break;
            case 9:
                dateAsString += "Oct ";
                break;
            case 10:
                dateAsString += "Nov ";
                break;
            case 11:
                dateAsString += "Dec ";
                break;
            default:
                break;
        }

        dateAsString += day + ", " + year;
        return dateAsString;
    }

    /**
     * DataPointContainer stores xData and yData for an
     * attendance in repository.
     */
    class DataPointContainer
    {
        /**
         * The date that the DataPointContainer represents.
         */
        Date date;

        /**
         * The data to be plotted on the X axis.
         */
        ArrayList<Integer> xData;

        /**
         * The data to be plotted on the Y axis.
         */
        ArrayList<Integer> yData;

        /**
         * The number of points for each DataPointContainer.
         */
        final int NUM_OF_POINTS = 11;

        /**
         * takes in an attendance to create the points of a data
         * point container.
         * @param attendance
         */
        public DataPointContainer(Attendance attendance)
        {
            xData = new ArrayList<Integer>();
            yData = new ArrayList<Integer>();

            for (int i = 0; i < NUM_OF_POINTS; i++)
            {
                xData.add(i * 10);
                yData.add(0);
            }

            int attendanceSize = attendance.getSize();
            int timeToAdd;

            for (int i = 0; i < attendanceSize; i++)
            {
                timeToAdd = attendance.getTimeAttended(i);
                addTimeToFrequency(timeToAdd);
            }

            date = attendance.getDate();
        }

        // adds a 1 to the frequency that the time corresponds to
        // (Y point represents).
        private void addTimeToFrequency(int timeToAdd)
        {
            /* %    Time Range      Index
             * 0  : X<7.5           0
             * 10 : 7.5=<x<15       1
             * 20 : 15=<x<22.5      2
             * 30 : 22.5=<x<30      3
             * 40 : 30=<x<37.5      4
             * 50 : 37.5=<x<45      5
             * 60 : 45=<x<52.5      6
             * 70 : 52.5=<x<60      7
             * 80 : 60=<x<67.5      8
             * 90 : 67.5=<x<75      9
             * 100: x>=75           10
             */

            if (timeToAdd > 75)
            {
                yData.set(10, yData.get(10) + 1);
            }
            else if (timeToAdd > 67.5)
            {
                yData.set(9, yData.get(9) + 1);
            }
            else if (timeToAdd > 60)
            {
                yData.set(8, yData.get(8) + 1);
            }
            else if (timeToAdd > 52.5)
            {
                yData.set(7, yData.get(7) + 1);
            }
            else if (timeToAdd > 45)
            {
                yData.set(6, yData.get(6) + 1);
            }
            else if (timeToAdd > 37.5)
            {
                yData.set(5, yData.get(5) + 1);
            }
            else if (timeToAdd > 30)
            {
                yData.set(4, yData.get(4) + 1);
            }
            else if (timeToAdd > 22.5)
            {
                yData.set(3, yData.get(3) + 1);
            }
            else if (timeToAdd > 15)
            {
                yData.set(2, yData.get(2) + 1);
            }
            else if (timeToAdd > 7.5)
            {
                yData.set(1, yData.get(1) + 1);
            }
            else
            {
                yData.set(0, yData.get(0) + 1);
            }
        }

        /**
         * returns the arrayList holding the XData.
         * @return xData
         */
        public ArrayList<Integer> getXData()
        {
            return xData;
        }

        /**
         * returns the arrayList holding the YData.
         * @return YData
         */
        public ArrayList<Integer> getYData()
        {
            return yData;
        }

        /**
         * returns the date that the DataPointContainer
         * represents.
         * @return date
         */
        public Date getDate()
        {
            return date;
        }
    }
}
