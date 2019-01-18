'use strict';

/**
 * Stuff.js controller
 *
 * @description: A set of functions called "actions" for managing `Stuff`.
 */

module.exports = {

  /**
   * Retrieve stuff records.
   *
   * @return {Object|Array}
   */

  find: async (ctx) => {
    if (ctx.query._q) {
      return strapi.services.stuff.search(ctx.query);
    } else {
      return strapi.services.stuff.fetchAll(ctx.query);
    }
  },

  /**
   * Retrieve a stuff record.
   *
   * @return {Object}
   */

  findOne: async (ctx) => {
    if (!ctx.params._id.match(/^[0-9a-fA-F]{24}$/)) {
      return ctx.notFound();
    }

    return strapi.services.stuff.fetch(ctx.params);
  },

  /**
   * Count stuff records.
   *
   * @return {Number}
   */

  count: async (ctx) => {
    return strapi.services.stuff.count(ctx.query);
  },

  /**
   * Create a/an stuff record.
   *
   * @return {Object}
   */

  create: async (ctx) => {
    return strapi.services.stuff.add(ctx.request.body);
  },

  /**
   * Update a/an stuff record.
   *
   * @return {Object}
   */

  update: async (ctx, next) => {
    return strapi.services.stuff.edit(ctx.params, ctx.request.body) ;
  },

  /**
   * Destroy a/an stuff record.
   *
   * @return {Object}
   */

  destroy: async (ctx, next) => {
    return strapi.services.stuff.remove(ctx.params);
  }
};
