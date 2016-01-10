package com.github.andyshaox.servlet.mapping;

import java.util.Objects;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 10, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface PageView extends View {
    public static PageView defaultPageView() {
        return new PageView() {
            private final View view = View.defaultView();

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof PageView) {
                    PageView that = (PageView) obj;
                    return Objects.equals(this.getView() , that.getView());
                } else return false;
            }

            @Override
            public Object getView() {
                return this.view.getView();
            }

            @Override
            public ViewProcess getViewProcess() {
                ViewProcess result = this.view.getViewProcess();
                return result.equals(ViewProcess.EMPTY)
                    ? (req , resp) -> req.getRequestDispatcher(this.getView().toString()).forward(req , resp) : result;
            }

            @Override
            public int hashCode() {
                return this.view.hashCode();
            }

            @Override
            public void setView(Object view) {
                this.view.setView(view);
            }

            @Override
            public void setViewProcess(ViewProcess viewProcess) {
                this.view.setViewProcess(viewProcess);
            }

            @Override
            public String toString() {
                return this.view.toString();
            }
        };
    }
}
