module FinanceGUI {
	requires itextpdf;
	requires javafx.controls;
	requires javafx.graphics;
	opens financeGUI to javafx.graphics;
}