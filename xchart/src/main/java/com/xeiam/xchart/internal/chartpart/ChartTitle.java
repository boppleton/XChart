/**
 * Copyright 2011-2013 Xeiam LLC.
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
package com.xeiam.xchart.internal.chartpart;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 * Chart Title
 */
public class ChartTitle implements ChartPart {

  /** parent */
  private final ChartPainter chartPainter;

  /** the bounds */
  private Rectangle bounds;

  /** the title text */
  private String text = ""; // default to ""

  /**
   * Constructor
   * 
   * @param chartPainter
   */
  public ChartTitle(ChartPainter chartPainter) {

    this.chartPainter = chartPainter;
  }

  /**
   * set the chart title's text
   * 
   * @param text
   */
  public void setText(String text) {

    if (text.trim().equalsIgnoreCase("")) {
      chartPainter.getStyleManager().setChartTitleVisible(false);
    } else {
      chartPainter.getStyleManager().setChartTitleVisible(true);
    }
    this.text = text;
  }

  /**
   * get the height of the chart title including the chart padding
   * 
   * @return
   */
  protected int getSizeHint() {

    if (chartPainter.getStyleManager().isChartTitleVisible()) {

      TextLayout textLayout = new TextLayout(text, chartPainter.getStyleManager().getChartTitleFont(), new FontRenderContext(null, true, false));
      Rectangle rectangle = textLayout.getPixelBounds(null, 0, 0);
      int titleHeight = (int) ((chartPainter.getStyleManager().isChartTitleVisible() ? rectangle.getHeight() : 0));
      return chartPainter.getStyleManager().getChartPadding() + 2 * chartPainter.getStyleManager().getChartTitlePadding() + titleHeight;
    } else {
      return chartPainter.getStyleManager().getChartPadding();
    }
  }

  @Override
  public void paint(Graphics2D g) {

    // bounds = new Rectangle();
    g.setFont(chartPainter.getStyleManager().getChartTitleFont());

    if (chartPainter.getStyleManager().isChartTitleVisible()) {

      // create rectangle first for sizing
      FontRenderContext frc = g.getFontRenderContext();
      TextLayout textLayout = new TextLayout(text, chartPainter.getStyleManager().getChartTitleFont(), frc);
      Rectangle rectangle = textLayout.getPixelBounds(null, 0, 0);

      int xOffset = (int) chartPainter.getPlot().getBounds().getX();
      int yOffset = chartPainter.getStyleManager().getChartPadding();

      if (chartPainter.getStyleManager().isChartTitleBoxVisible()) {

        // paint the chart title box
        int chartTitleBoxWidth = (int) chartPainter.getPlot().getBounds().getWidth();
        int chartTitleBoxHeight = (int) (rectangle.getHeight() + 2 * chartPainter.getStyleManager().getChartTitlePadding());

        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        g.setColor(chartPainter.getStyleManager().getChartTitleBoxBorderColor());
        g.drawRect(xOffset - 1, yOffset, chartTitleBoxWidth, chartTitleBoxHeight);
        g.setColor(chartPainter.getStyleManager().getChartTitleBoxBackgroundColor());
        g.fillRect(xOffset, yOffset + 1, chartTitleBoxWidth - 1, chartTitleBoxHeight - 1);
      }

      // paint title
      xOffset = (int) (chartPainter.getPlot().getBounds().getX() + (chartPainter.getPlot().getBounds().getWidth() - rectangle.getWidth()) / 2.0);
      yOffset = (int) (chartPainter.getStyleManager().getChartPadding() - rectangle.getY() + chartPainter.getStyleManager().getChartTitlePadding());

      // bounds = new Rectangle(xOffset, yOffset + ((int) rectangle.getY()), (int) rectangle.getWidth(), (int) (rectangle.getHeight()));
      // g.setColor(Color.green);
      // g.draw(bounds);

      g.setColor(chartPainter.getStyleManager().getChartFontColor());
      textLayout.draw(g, xOffset, yOffset);
    }

  }

  @Override
  public Rectangle getBounds() {

    return null; // this should never be needed
  }

  @Override
  public ChartPainter getChartPainter() {

    return chartPainter;
  }
}