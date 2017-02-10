package com.abdymalikmulky.abdroidmvp.app.data.news;

import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Beritas;
import com.abdymalikmulky.abdroidmvp.util.EndpointUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by abdymalikmulky on 1/27/17.
 */

public interface NewsApi {
    public static final String URI_GET = EndpointUtils.ENDPOINT_LIST;
    public static final String URI_DETAIL = EndpointUtils.ENDPOINT_DETAIL;

    @GET(URI_GET)
    public Call<Beritas> getList();

    @GET(URI_DETAIL)
    public Call<Beritas> getDetail(@Path("slug") String slug);

}
