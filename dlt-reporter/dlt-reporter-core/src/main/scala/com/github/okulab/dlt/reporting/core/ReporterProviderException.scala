package com.github.okulab.dlt.reporting.core

/**
  * @author #okulab-developers<team.dev@okulab.co>
  */
class ReporterProviderException(msge: String, cause: Option[Throwable])
  extends Exception(msge, cause.orNull) with Serializable

object ReporterProviderException {
  def unknown(name: String, cause: Option[Throwable]): ReporterProviderException =
    new ReporterProviderException(s"$name is not a valid reporter type", cause)

  def homonym(name: String, cause: Option[Throwable]): ReporterProviderException =
    new ReporterProviderException(s"There are multiple reporters with the name $name, please check if build is correct", cause)

  def wrongDefinition(name: String, cause: Option[Throwable]): ReporterProviderException =
    new ReporterProviderException(s"We are unable to instantiate the reporter $name, please check your configuration", cause)
}
