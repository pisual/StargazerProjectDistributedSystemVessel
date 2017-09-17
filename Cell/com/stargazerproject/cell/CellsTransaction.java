package com.stargazerproject.cell;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public interface CellsTransaction<E, A> {
	public boolean method(Optional<Cache<E, A>> cache);
	public boolean fallBack(Optional<Cache<E, A>> cache, Throwable throwable);
}
