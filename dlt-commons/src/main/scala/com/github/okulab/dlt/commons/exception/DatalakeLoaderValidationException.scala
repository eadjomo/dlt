package com.github.okulab.dlt.commons.exception

/**
  * DatalakeLoaderValidationException
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
class DatalakeLoaderValidationException extends DatalakeException {
  projetName = "Datalake Loader"
  language = "Scala"
}

object DatalakeLoaderValidationException extends DatalakeLoaderValidationException {

  def apply(message: String): DatalakeLoaderValidationException = {
    super.init(message)
    this
  }

  def apply(message: String, cause: Throwable): DatalakeLoaderValidationException = {
    super.init(message, cause)
    this
  }

  def apply(cause: Throwable): DatalakeLoaderValidationException = {
    super.init(cause)
    this
  }
}
