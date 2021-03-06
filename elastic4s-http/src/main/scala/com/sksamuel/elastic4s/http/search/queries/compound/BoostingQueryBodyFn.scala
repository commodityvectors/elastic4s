package com.sksamuel.elastic4s.http.search.queries.compound

import com.sksamuel.elastic4s.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s.searches.queries.BoostingQuery

object BoostingQueryBodyFn {
  def apply(q: BoostingQuery): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()
    builder.startObject("boosting")
    builder.rawField("positive", QueryBuilderFn(q.positiveQuery))
    builder.rawField("negative", QueryBuilderFn(q.negativeQuery))
    q.negativeBoost.foreach(builder.field("negative_boost", _))
    q.boost.foreach(builder.field("boost", _))
    builder.endObject()
    builder.endObject()
  }
}
