package estadistica;
import java.awt.Color;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.SortOrder;

/**
 * A demo showing the creation of a pareto chart.
 *
 */
public class Pareto extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public Pareto(float[] frecuencias, String[] categorias, int tam) {

        super("Pareto");



        DefaultKeyedValues data = new DefaultKeyedValues();

        for (int i = 0; i < tam; i++) {
            data.addValue(categorias[i], frecuencias[i]);

        }


        data.sortByValues(SortOrder.DESCENDING);
        KeyedValues cumulative = DataUtilities.getCumulativePercentages(data);
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Categorias", data);

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Diagrama de Pareto",  // chart title
            "Categorias",                     // domain axis label
            "Frecuencia Absoluta",                     // range axis label
            dataset,                        // data
            PlotOrientation.VERTICAL,
            true,                           // include legend
            true,
            false
        );


        // set the background color for the chart...
        chart.setBackgroundPaint(new Color(0xBBBBDD));

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.02);
        domainAxis.setUpperMargin(0.02);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();

        CategoryDataset dataset2 = DatasetUtilities.createCategoryDataset(
            "Frec. Relativa Acumulada", cumulative
        );
        NumberAxis axis2 = new NumberAxis("Porcentaje");
        axis2.setNumberFormatOverride(NumberFormat.getPercentInstance());
        plot.setRangeAxis(1, axis2);
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        plot.mapDatasetToRangeAxis(1, 1);

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
        ChartFrame chartPanel = new ChartFrame(" ",chart);
        chartPanel.pack();
        chartPanel.setVisible(true);

    }
   }