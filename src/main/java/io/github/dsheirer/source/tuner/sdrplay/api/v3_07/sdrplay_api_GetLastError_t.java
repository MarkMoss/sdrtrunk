/*
 * *****************************************************************************
 * Copyright (C) 2014-2023 Dennis Sheirer
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

// Generated by jextract

package io.github.dsheirer.source.tuner.sdrplay.api.v3_07;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentScope;

/**
 * {@snippet :
 * struct * (*sdrplay_api_GetLastError_t)(struct * device);
 * }
 */
public interface sdrplay_api_GetLastError_t {

    java.lang.foreign.MemorySegment apply(java.lang.foreign.MemorySegment device);
    static MemorySegment allocate(sdrplay_api_GetLastError_t fi, SegmentScope scope) {
        return RuntimeHelper.upcallStub(constants$3.sdrplay_api_GetLastError_t_UP$MH, fi, constants$3.sdrplay_api_GetLastError_t$FUNC, scope);
    }
    static sdrplay_api_GetLastError_t ofAddress(MemorySegment addr, SegmentScope scope) {
        MemorySegment symbol = MemorySegment.ofAddress(addr.address(), 0, scope);
        return (java.lang.foreign.MemorySegment _device) -> {
            try {
                return (java.lang.foreign.MemorySegment)constants$3.sdrplay_api_GetLastError_t_DOWN$MH.invokeExact(symbol, _device);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


