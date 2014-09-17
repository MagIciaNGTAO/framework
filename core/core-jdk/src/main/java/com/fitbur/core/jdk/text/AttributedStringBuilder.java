/*
 * Copyright 2014 saden.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fitbur.core.jdk.text;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Map;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

/**
 *
 * @author saden
 */
@PerLookup
@Service
public class AttributedStringBuilder {

    public IteratorBuilder text(AttributedCharacterIterator text) {
        return new IteratorBuilder(text);
    }

    public TextBuilder text(String text) {
        return new TextBuilder(text);
    }

    public class IteratorBuilder {

        private final AttributedCharacterIterator text;
        private Integer begin;
        private Integer end;
        private AttributedCharacterIterator.Attribute[] attributes;

        private IteratorBuilder(AttributedCharacterIterator text) {
            this.text = text;
        }

        public IteratorBuilder begin(int begin) {
            this.begin = begin;

            return this;
        }

        public IteratorBuilder end(int end) {
            this.end = end;

            return this;
        }

        public IteratorBuilder attributes(
                AttributedCharacterIterator.Attribute... attributes) {
            this.attributes = attributes;

            return this;
        }

        public AttributedString build() {
            if (begin == null) {
                begin = text.getBeginIndex();
            }

            if (end == null) {
                end = text.getEndIndex();
            }

            return new AttributedString(text, begin, end, attributes);
        }
    }

    public class TextBuilder {

        private final String text;

        public TextBuilder(String text) {
            this.text = text;
        }

        public AttributeBulder attributes(
                Map<? extends AttributedCharacterIterator.Attribute, ?> attributes) {
            return new AttributeBulder(text, attributes);
        }

        public AttributedString build() {
            return new AttributedString(text);
        }
    }

    public class AttributeBulder {

        private final String text;
        private final Map<? extends AttributedCharacterIterator.Attribute, ?> attributes;

        private AttributeBulder(String text,
                Map<? extends AttributedCharacterIterator.Attribute, ?> attributes) {
            this.text = text;
            this.attributes = attributes;
        }

        public AttributedString build() {
            return new AttributedString(text, attributes);
        }
    }

}
