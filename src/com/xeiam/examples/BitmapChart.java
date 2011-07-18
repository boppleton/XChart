/**
 * Copyright 2011 Xeiam LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xeiam.examples;

import com.xeiam.xcharts.BitmapEncoder;
import com.xeiam.xcharts.Chart;

/**
 * @author timmolter
 */
public class BitmapChart {

    private static final double[] xData = { 0.0, 1.0, 2.0 };
    private static final double[] yData = { 0.0, 1.0, 2.0 };

    public static void main(String[] args) {

        // Create Chart
        Chart chart = new Chart(500, 400);
        chart.setChartTitle("Sample Chart");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.addSeries("y(x)", xData, yData);

        try {
            BitmapEncoder.savePNG(chart, "/test/Chart_Small.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}