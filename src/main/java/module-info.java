/*
 * Copyright (c) 2024 by Bern University of Applied Sciences
 * All rights reserved. Unauthorized reproduction and distribution of this file
 * for purposes other than personal educational use is strictly prohibited. This
 * prohibition includes uploading this file, in whole or in part, to any AI services,
 * including but not limited to ChatGPT.
 */
module quadstrike {
    requires javafx.controls;
    requires javafx.fxml;

    opens ch.bfh.bti3001.quadstrike to javafx.fxml;

    exports ch.bfh.bti3001.quadstrike;
}
