package cn.nkpro.easis.data.mybatis.pagination.dialect;

import cn.nkpro.easis.annotation.Keep;

@Keep
public class SybaseDialect extends Dialect{

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return false;
	}

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		throw new UnsupportedOperationException( "paged queries not supported" );
	}

}
