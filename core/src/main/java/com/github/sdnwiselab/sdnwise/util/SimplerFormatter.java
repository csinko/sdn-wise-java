/*
 * Copyright (C) 2015 Seby
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.sdnwiselab.sdnwise.util;

import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.logging.*;

/**
 * The SimplerFormatter class formats all the logs. The format used is: HH:mm:ss
 * [LEVEL] [SOURCE] Message
 *
 * @author Sebastiano Milardo
 */
public class SimplerFormatter extends Formatter {

    private final String name;
    private final SimpleDateFormat formatter;

    public SimplerFormatter(String name) {
        this.formatter = new SimpleDateFormat("HH:mm:ss");
        this.name = name;
    }

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder(formatter
                .format(new Date(record.getMillis())));
        sb.append(" [")
                .append(record.getLevel())
                .append("] [")
                .append(name)
                .append("] ")
                .append(formatMessage(record));

        if (record.getThrown() != null) {
            try {
                StringWriter sw = new StringWriter();
                try (PrintWriter pw = new PrintWriter(sw)) {
                    record.getThrown().printStackTrace(pw);
                }
                sb.append(sw.toString());
            } catch (Exception ex) {
            }
        }
        return sb.append("\n").toString();
    }
}