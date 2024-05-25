package amit.yadav.domain

import java.io.IOException

/**
 * Wrapper for [IOException] related to network communication.
 * Allows to pass differentiation whether an exception is related to network or not without additional classes on
 * subsequent levels of abstraction.
 */
class NetworkIOException(cause: Throwable) : IOException(cause)