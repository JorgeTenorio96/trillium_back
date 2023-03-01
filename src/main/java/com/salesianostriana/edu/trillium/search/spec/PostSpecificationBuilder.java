package com.salesianostriana.edu.trillium.search.spec;

import com.salesianostriana.edu.trillium.post.model.Post;
import com.salesianostriana.edu.trillium.search.util.SearchCriteria;

import java.util.List;

public class PostSpecificationBuilder extends GenericSpecificationBuilder<Post>{

    public PostSpecificationBuilder(List<SearchCriteria> params) {
        super(params, Post.class);
    }

}
